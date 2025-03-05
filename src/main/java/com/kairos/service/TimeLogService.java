package com.kairos.service;

import java.util.List;

import com.kairos.domain.timelog.TimeLog;

public interface TimeLogService {

	public List<TimeLog> getTimeLogs();
	
	public TimeLog findById(Integer id);
	
	public TimeLog createTimeLog(TimeLog timeLog, Integer taskId, Integer userId);
	
	public void updateTimeLog(TimeLog timeLog, Integer taskId, Integer userId);
	
	public void deleteTimeLogById(Integer id);
	
}
