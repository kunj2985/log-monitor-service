package com.log.monitor.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.log.monitor.constant.LogMonitorConstants;
import com.log.monitor.entity.LogEventEntity;
import com.log.monitor.exception.LogFileFormatException;
import com.log.monitor.exception.LogFileNotFoundException;
import com.log.monitor.exception.LogMonitorException;
import com.log.monitor.mapper.LogMonitorMapper;
import com.log.monitor.model.LogEventModel;
import com.log.monitor.model.LogModel;
import com.log.monitor.repository.LogMonitorRepository;
import com.log.monitor.utility.LogMonitorUtilities;

@Service
public class LogMonitorServiceImpl implements LogMonitorService {

	private final Logger logger = LoggerFactory.getLogger(LogMonitorServiceImpl.class);

	@Autowired
	LogMonitorRepository repository;

	@Value("${log.file.path}")
	private String filePath;

	@Value("${duration.threshold}")
	private Long durationThreshold;

	List<LogEventModel> events = new ArrayList<LogEventModel>();

	@Override
	public List<LogEventModel> processLogs() {

		// Creating parallel stream so that the logger and the file can be process by
		// multiple treads simultaneously.
		try (Stream<String> lines = Files.lines(Paths.get(getLogFilePath())).parallel()) {

			// Process the log lines and Saving all events in DB
			List<LogEventEntity> savedEvents = repository.saveAll(LogMonitorMapper.toEntity(processLogLines(lines)));

			events = LogMonitorMapper.toModel(savedEvents);
			LogMonitorMapper.toModel(savedEvents);
			logger.info(LogMonitorConstants.LOGGER_MSG_EVENT_DATA_INSERTED);
		} catch (JsonSyntaxException e) {
			logger.error(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e.getMessage());
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
			throw new LogFileFormatException(LogMonitorConstants.LOGGER_MSG_FILE_NOT_FORMATTED);
		} catch (IOException e) {
			logger.error(LogMonitorConstants.LOG_ERROR_MESSAGE, e.getMessage());
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
			throw new LogFileNotFoundException(LogMonitorConstants.LOGGER_MSG_FILE_NOT_FOUND);
		} catch (Exception e) {
			logger.error(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e.getMessage());
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
			throw new LogMonitorException(LogMonitorConstants.UNEXPECTED_ERROR);
		}

		return events;
	}

	/**
	 * This method is used to process a single logger and calculate the event
	 * duration
	 * 
	 * @param lines Stream of String
	 * @return List of log events
	 */
	private synchronized Vector<LogEventModel> processLogLines(Stream<String> lines) throws JsonSyntaxException {

		logger.debug(LogMonitorConstants.LOGGER_MSG_LOG_LINE_PROCESSING);
		// Map to hold the loggers
		Map<String, LogModel> logInputReaderMap = new ConcurrentHashMap<String, LogModel>();

		// List to collect events
		Vector<LogEventModel> logEvents = new Vector<LogEventModel>();

		Gson gson = new Gson();

		// Using stream API to iterate and process the logger line by line
		lines.forEach(line -> {

			Optional<LogModel> logLine = Optional.ofNullable(gson.fromJson(line, LogModel.class));

			if (logLine.isPresent()) {
				LogModel log = logLine.get();

				logger.debug(LogMonitorConstants.LOGGER_MSG_LINE_PROCESSING_WITH_ID + log.getId());

				if (logInputReaderMap.containsKey(log.getId())) {
					LogModel logModel2 = logInputReaderMap.get(log.getId());

					if (LogMonitorUtilities.isLogStateNotMatched(log, logModel2)) {
						// Calculating event duration here.
						Long duration = Math.abs(log.getTimestamp() - logModel2.getTimestamp());

						// Adding in the list which later need to persist in DB
						logEvents.add(new LogEventModel(log.getId(), duration, log.getType(), log.getHost(),
								(duration > durationThreshold) ? true : false));
					}
					// remove log line for which the duration has been calculated
					logInputReaderMap.remove(log.getId());

				} else {
					logInputReaderMap.put(log.getId(), log);
				}

				logger.debug(LogMonitorConstants.LOGGER_MSG_LOG_LINE_PROCESSING);
			}

		});

		logger.info(LogMonitorConstants.LOGGER_MSG_ALL_LOGS_PROCESSED);
		return logEvents;
	}

	/**
	 * This method fetch file path firstly from system/vm argument otherwise from
	 * application properties
	 * 
	 * @return
	 */
	private String getLogFilePath() {
		String path = System.getProperty(LogMonitorConstants.LOG_FILE_PATH_KEY);
		if (StringUtils.hasText(path)) {
			return path;
		} else {
			return filePath;
		}
	}

}
