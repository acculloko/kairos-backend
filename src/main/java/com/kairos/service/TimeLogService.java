package com.kairos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.kairos.domain.timelog.TimeLog;

public interface TimeLogService {

	public List<TimeLog> getTimeLogs();
	
	public TimeLog findById(Integer id);
	
	public List<TimeLog> getTimeLogsByUserId(Integer id);
	
	public double getTotalLoggedHours();
	
	public double getTotalLoggedHoursByUser(Integer userId);
	
	public double getTotalLoggedHoursByUserByPeriod(Integer userId, LocalDateTime startDate, LocalDateTime endDate);
	
	public TimeLog createTimeLog(TimeLog timeLog, Integer taskId, Integer userId);
	
	public void updateTimeLog(TimeLog timeLog, Integer taskId, Integer userId);
	
	public void deleteTimeLogById(Integer id);
	
}
