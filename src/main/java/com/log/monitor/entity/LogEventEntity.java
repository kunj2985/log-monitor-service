package com.log.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_detail")
public class LogEventEntity {

	@Id
	private String eventId;
	private Long eventDuration;
	private String type;
	private String host;
	private Boolean alert;

	public LogEventEntity() {
	}

	public LogEventEntity(String eventId, Long eventDuration, String type, String host, Boolean alert) {

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
