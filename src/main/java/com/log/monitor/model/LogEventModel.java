package com.log.monitor.model;

public class LogEventModel {

	private String eventId;
	private Long eventDuration;
	private String type;
	private String host;
	private Boolean alert;

	public LogEventModel(String eventId, Long eventDuration, String type, String host, Boolean alert) {

		this.eventId = eventId;
		this.eventDuration = eventDuration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	public String getEventId() {
		return eventId;
	}

	public Long getEventDuration() {
		return eventDuration;
	}


	public String getType() {
		return type;
	}


	public String getHost() {
		return host;
	}

	public Boolean getAlert() {
		return alert;
	}

}
