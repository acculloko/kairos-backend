package com.kairos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kairos.domain.task.Task;
import com.kairos.domain.timelog.TimeLog;
import com.kairos.domain.user.User;
import com.kairos.repository.TimeLogRepository;
import com.kairos.service.TaskService;
import com.kairos.service.TimeLogService;
import com.kairos.service.UserService;

@Service
public class TimeLogServiceImpl implements TimeLogService {
	
	@Autowired
	private TimeLogRepository timeLogRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public List<TimeLog> getTimeLogs() {
		return timeLogRepository.findAll();
	}

	@Override
	public TimeLog findById(Integer id) {
		Optional<TimeLog> timeLog = timeLogRepository.findById(id);
		return timeLog.orElse(null);
	}

	@Override
	public TimeLog createTimeLog(TimeLog timeLog, Integer taskId, Integer userId) {
		User user = userService.findById(userId);
		Task task = taskService.findById(taskId);
		
		if (user != null) {
			timeLog.setUser(user);
		} else {
			throw new IllegalArgumentException("User not found");
		}
		
		if (task != null) {
			timeLog.setTask(task);
		} else {
			throw new IllegalArgumentException("Task not found");
		}
		
		return timeLogRepository.save(timeLog);
	}

	@Override
	public void updateTimeLog(TimeLog timeLog, Integer taskId, Integer userId) {
		TimeLog currentTimeLog = this.findById(timeLog.getId());
		
		currentTimeLog.setDescription(timeLog.getDescription());
		currentTimeLog.setStart_date(timeLog.getStart_date());
		currentTimeLog.setEnd_date(timeLog.getEnd_date());
		
		User user = userService.findById(userId);
		Task task = taskService.findById(taskId);
		
		if (user != null) {
			currentTimeLog.setUser(user);
		} else {
			throw new IllegalArgumentException("User not found");
		}
		
		if (task != null) {
			currentTimeLog.setTask(task);
		} else {
			throw new IllegalArgumentException("Task not found");
		}
		
		timeLogRepository.save(currentTimeLog);
	}

	@Override
	public void deleteTimeLogById(Integer id) {
		timeLogRepository.deleteById(id);
	}

}
