package com.log.monitor.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.log.monitor.controller.LogMonitorController;
import com.log.monitor.model.EventResponseModel;
import com.log.monitor.model.LogEventModel;

@SpringBootTest
class LogMonitorServiceControllerTests {

	@Autowired
	LogMonitorController controller;

	@MockBean
	LogMonitorServiceImpl logMonitorService;

	@Test
	public void startLogAnalysisTest() {

		LogEventModel log = new LogEventModel("id1", 4L, "", "", false);
		List<LogEventModel> events = new ArrayList<>();
		events.add(log);
		Mockito.when(logMonitorService.processLogs()).thenReturn(events);
		ResponseEntity<EventResponseModel> response = controller.startLogAnalysis();
		assertNotNull(response);
	}

	@Test
	public void startLogAnalysisExceptionTest() {
		try {
			Mockito.when(logMonitorService.processLogs()).thenReturn(new ArrayList<>());
			ResponseEntity<EventResponseModel> response = controller.startLogAnalysis();
			assertNotNull(response);
		} catch (Exception e) {

		}
	}

}
