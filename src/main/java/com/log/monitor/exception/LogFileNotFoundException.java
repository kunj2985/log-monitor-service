package com.log.monitor.exception;

public class LogFileNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5825239624016176752L;

	public LogFileNotFoundException(String msg) {
		    super(msg);
		  }
}
