package com.kairos.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kairos.domain.task.Task;
import com.kairos.domain.task.TaskStatus;
import com.kairos.repository.TaskRepository;
import com.kairos.service.ProjectService;
import com.kairos.service.TaskService;
import com.kairos.service.UserService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;

	@Override
	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task findById(Integer id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.orElse(null);
	}

	@Override
	public List<Task> getTasksByProjectId(Integer id) {
		return taskRepository.findAllByProjectId(id);
	}
	
	@Override
	public List<Task> getTasksByUserId(Integer id) {
		return taskRepository.findAllByResponsibleUserId(id);
	}
	
	@Override
	public List<Task> getOverdueTasks() {
		List<TaskStatus> excludedStatuses = Arrays.asList(TaskStatus.DONE, TaskStatus.CANCELLED);
        return taskRepository.findOverdueTasks(LocalDate.now(), excludedStatuses);
	}

	@Override
	public List<Task> getActiveTasksByUserId(Integer id) {
		List<TaskStatus> excludedStatuses = Arrays.asList(TaskStatus.DONE, TaskStatus.CANCELLED);
        return taskRepository.findAllExceptDoneAndCancelledByUser(excludedStatuses, id);
	}
	
	@Override
	public Long getTotalActiveTasks() {
		return taskRepository.countTasksNotDoneOrCancelled();
	}

	@Override
	public Long getTotalActiveTasksByUser(Integer userId) {
		return taskRepository.countTasksNotDoneOrCancelledByUser(userId);
	}

	@Override
	public Task createTask(Task task, Integer projectId, Integer userId) {
		task.setProject(null);
		task.setResponsible_user(null);
		
		if (projectId != null) {
			task.setProject(projectService.findById(projectId));
		}
		
		if (userId != null) {
			task.setResponsible_user(userService.findById(userId));
		}
		
		return taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task, Integer projectId, Integer userId) {
		Task currentTask = this.findById(task.getId());
		
		currentTask.setProject(null);
		currentTask.setName(task.getName());
		currentTask.setDescription(task.getDescription());
		currentTask.setStart_date(task.getStart_date());
		currentTask.setEnd_date(task.getEnd_date());
		currentTask.setStatus(task.getStatus());
		currentTask.setResponsible_user(null);
		
		if (projectId != null) {
			currentTask.setProject(projectService.findById(projectId));
		}
		
		if (userId != null) {
			currentTask.setResponsible_user(userService.findById(userId));
		}
		
		taskRepository.save(currentTask);
		
	}

	@Override
	public void deleteTaskById(Integer id) {
		taskRepository.deleteById(id);
	}

}
