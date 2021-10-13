package com.log.monitor.model;

public class EventResponseModel{
	private Object data;
	private String statusCode;
	private String message;
	private String status;
	
	public EventResponseModel(Object data, String statusCode, String message, String status) {
		
		this.data = data;
		this.statusCode = statusCode;
		this.message = message;
		this.status = status;
	}
	public EventResponseModel() {
		
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
