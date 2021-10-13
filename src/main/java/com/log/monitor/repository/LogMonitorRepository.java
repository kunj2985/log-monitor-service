package com.log.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.monitor.entity.LogEventEntity;

@Repository
public interface LogMonitorRepository extends JpaRepository<LogEventEntity, String>{

}
