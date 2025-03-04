package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.domain.timelog.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {

}
