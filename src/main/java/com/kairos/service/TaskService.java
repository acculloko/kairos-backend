package com.kairos.service;

import java.util.List;

import com.kairos.domain.task.Task;

public interface TaskService {

	public List<Task> getTasks();
	
	public Task findById(Integer id);
	
	public Task createTask(Task task, Integer projectId, Integer userId);
	
	public void updateTask(Task task, Integer projectId, Integer userId);
	
	public void deleteTaskById(Integer id);
	
}
