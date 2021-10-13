package com.log.monitor.exception;

public class LogFileFormatException extends RuntimeException {

	private static final long serialVersionUID = -4724966767882493904L;

	public LogFileFormatException(String messageWithErrorString) {
		super(messageWithErrorString);
	}
}
