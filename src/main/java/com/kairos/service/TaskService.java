package com.kairos.service;

import java.util.List;

import com.kairos.domain.task.Task;
import com.kairos.domain.task.dto.TaskStatusCountDTO;

public interface TaskService {

	public List<Task> getTasks();
	
	public Task findById(Integer id);
	
	public List<Task> getTasksByProjectId(Integer id);
	
	public List<Task> getTasksByUserId(Integer id);
	
	public List<Task> getOverdueTasks();

	public List<Task> getActiveTasksByUserId(Integer id);
	
	public List<TaskStatusCountDTO> getTaskStatusCountsByProjectId(Integer id);
	
	public Long getTotalActiveTasks();
	
	public Long getTotalActiveTasksByUser(Integer userId);
	
	public Task createTask(Task task, Integer projectId, Integer userId);
	
	public void updateTask(Task task, Integer projectId, Integer userId);
	
	public void deleteTaskById(Integer id);
	
}
