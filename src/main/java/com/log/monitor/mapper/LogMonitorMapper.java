package com.log.monitor.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.log.monitor.entity.LogEventEntity;
import com.log.monitor.model.LogEventModel;

public interface LogMonitorMapper {

	public static List<LogEventEntity> toEntity(List<LogEventModel> models) {

		List<LogEventEntity> events = new ArrayList<LogEventEntity>();
		if (null != models) {
			events = models.stream().map(item -> new LogEventEntity(item.getEventId(), item.getEventDuration(),
					item.getType(), item.getHost(), item.getAlert())).collect(Collectors.toList());
		}
		return events;
	}

	public static List<LogEventModel> toModel(List<LogEventEntity> entity) {

		List<LogEventModel> events = new ArrayList<LogEventModel>();
		if (null != entity) {
			events = entity.stream().map(item -> new LogEventModel(item.getEventId(), item.getEventDuration(),
					item.getType(), item.getHost(), item.getAlert())).collect(Collectors.toList());
		}
		return events;
	}
}
