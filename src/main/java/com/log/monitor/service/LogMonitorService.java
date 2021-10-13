package com.log.monitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.log.monitor.model.LogEventModel;

@Service
public interface LogMonitorService {

	/**
	 * This method being used to process loggers and calculating duration of the events. 
	 * @return List of events
	 */
	List<LogEventModel> processLogs();
}
