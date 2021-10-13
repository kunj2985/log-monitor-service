package com.log.monitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.log.monitor.constant.LogMonitorConstants;
import com.log.monitor.model.EventResponseModel;

@ControllerAdvice
public class LogMonitorControllerExceptionHandler {

	@ExceptionHandler(value = {LogFileNotFoundException.class})
	  public ResponseEntity<EventResponseModel> logFileNotFoundException(LogFileNotFoundException ex, WebRequest request) {
		EventResponseModel message = new EventResponseModel( null,
		        LogMonitorConstants.ERROR_CODE_FILE_NOT_FOUND,
		        ex.getMessage(),
		        LogMonitorConstants.STATUS_FAILURE);
		    return new ResponseEntity<EventResponseModel>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	  }
	
	@ExceptionHandler(value = {LogFileFormatException.class})
	  public ResponseEntity<EventResponseModel> logFileFormatException(LogFileFormatException ex, WebRequest request) {
		EventResponseModel message = new EventResponseModel( null,
		        LogMonitorConstants.ERROR_CODE_FILE_FORMAT,
		        ex.getMessage(),
		        LogMonitorConstants.STATUS_FAILURE);
		    return new ResponseEntity<EventResponseModel>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	  }
	
	@ExceptionHandler(value = {LogMonitorException.class})
	  public ResponseEntity<EventResponseModel> logFileFormatException(LogMonitorException ex, WebRequest request) {
		EventResponseModel message = new EventResponseModel( null,
		        LogMonitorConstants.ERROR_CODE_COMMON,
		        ex.getMessage(),
		        LogMonitorConstants.STATUS_FAILURE);
		    return new ResponseEntity<EventResponseModel>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	  }
}
