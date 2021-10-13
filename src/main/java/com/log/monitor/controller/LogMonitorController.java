package com.log.monitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.monitor.constant.LogMonitorConstants;
import com.log.monitor.model.EventResponseModel;
import com.log.monitor.model.LogEventModel;
import com.log.monitor.service.LogMonitorService;

@RestController
@RequestMapping("/log-monitor/")
public class LogMonitorController {

	@Autowired
	LogMonitorService logMonitorService;
	
	@GetMapping(value = "start")
	public ResponseEntity<EventResponseModel> startLogAnalysis() {
		List<LogEventModel> processLogs = logMonitorService.processLogs();
		return new ResponseEntity<EventResponseModel>(
				new EventResponseModel(processLogs, LogMonitorConstants.SUCCESS_CODE_OK,
						LogMonitorConstants.SUCCESS_MESSAGE, LogMonitorConstants.STATUS_SUCCESS), HttpStatus.OK);
	}

}
