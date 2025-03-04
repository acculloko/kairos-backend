package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.model.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {

}
