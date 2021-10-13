package com.log.monitor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.ResourceUtils;

import com.google.gson.JsonSyntaxException;
import com.log.monitor.constant.LogMonitorConstants;
import com.log.monitor.entity.LogEventEntity;
import com.log.monitor.model.EventResponseModel;
import com.log.monitor.model.LogEventModel;
import com.log.monitor.repository.LogMonitorRepository;

@SpringBootTest
public class LogMonitorServiceImplTest {
	private final Logger logger = LoggerFactory.getLogger(LogMonitorServiceImpl.class);

	@Autowired
	LogMonitorServiceImpl logMonitorService;

	@MockBean
	LogMonitorRepository logMonitorRepository;

	@Test
	public void processLogsTest() throws IOException {
		
			String path = ResourceUtils.getFile("src/test/resources/logFile.txt").getPath();
			System.setProperty("log.file.path", path);
			Mockito.when(logMonitorRepository.saveAll(Mockito.anyList())).thenReturn(prepareData());
			List<LogEventModel> processLogs = logMonitorService.processLogs();
			assertEquals(3, processLogs.size());
		
	}

	@Test
	public void processLogsExceptionJsonTest() {

		try {
			String path = ResourceUtils.getFile("src/test/resources/logFileFormatError.txt").getPath();
			System.setProperty("log.file.path", path);
			Mockito.when(logMonitorRepository.saveAll(Mockito.anyList())).thenThrow(new JsonSyntaxException(""));
			logMonitorService.processLogs();
		} catch (Exception e) {
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
		}
	}

	@Test
	public void processLogsIOExceptionTest() {

		try {
			System.clearProperty("log.file.path");
			//System.setProperty("log.file.path", "/WrongPath");
			Mockito.when(logMonitorRepository.saveAll(Mockito.anyList())).thenReturn(prepareData());
			logMonitorService.processLogs();
		} catch (Exception e) {
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
		}
	}
	
	@Test
	public void processLogsExceptionTest() {

		try {
			String path = ResourceUtils.getFile("src/test/resources/logFile.txt").getPath();
			System.setProperty("log.file.path", path);
			Mockito.when(logMonitorRepository.saveAll(Mockito.anyList())).thenThrow(new NullPointerException(""));
			logMonitorService.processLogs();
		} catch (Exception e) {
			logger.debug(LogMonitorConstants.LOG_ERROR_MESSAGE_FORMAT, e);
		}
	}

	private List<LogEventEntity> prepareData() {

		EventResponseModel eventRes = new EventResponseModel();
		eventRes.setData(new Object());
		eventRes.setMessage("Hi");
		eventRes.setStatus("Sucess");
		eventRes.setStatusCode("200");

		eventRes.getData();
		eventRes.getMessage();
		eventRes.getStatus();
		eventRes.getStatusCode();
		
		LogEventEntity logEventEntity = new LogEventEntity();
		List<LogEventEntity> list = new ArrayList<LogEventEntity>();
		list.add(logEventEntity);
		list.add(logEventEntity);
		list.add(logEventEntity);
		return list;
	}
}
