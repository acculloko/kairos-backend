package com.kairos.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.domain.task.Task;
import com.kairos.domain.task.dto.TaskCreationDTO;
import com.kairos.domain.task.dto.TaskResponseDTO;
import com.kairos.domain.task.dto.TaskStatusCountDTO;
import com.kairos.domain.task.dto.TaskStatusResponseDTO;
import com.kairos.mapper.TaskMapper;
import com.kairos.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@GetMapping
	public ResponseEntity<List<TaskResponseDTO>> getTasks() {
		List<Task> list = taskService.getTasks();
		
		return ResponseEntity.ok().body(taskMapper.taskListToTaskResponseDto(list));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable Integer id) {
		Task task = taskService.findById(id);
		
		return ResponseEntity.ok().body(taskMapper.taskToTaskResponseDto(task));
	}
	
	@GetMapping("/count-by-status/{id}")
	public ResponseEntity<TaskStatusResponseDTO> getTaskCountByStatus(@PathVariable Integer id) {
        List<TaskStatusCountDTO> counts = taskService.getTaskStatusCountsByProjectId(id);

        if (counts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new TaskStatusResponseDTO(counts));
    }
	
	@GetMapping("/project/{id}")
	public ResponseEntity<List<TaskResponseDTO>> getTasksByProjectId(@PathVariable Integer id) {
		List<Task> list = taskService.getTasksByProjectId(id);
		
		return ResponseEntity.ok().body(taskMapper.taskListToTaskResponseDto(list));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<TaskResponseDTO>> getTasksByUserId(@PathVariable Integer id) {
		List<Task> list = taskService.getTasksByUserId(id);
		
		return ResponseEntity.ok().body(taskMapper.taskListToTaskResponseDto(list));
	}
	
	@GetMapping("/overdue")
	public ResponseEntity<List<TaskResponseDTO>> getOverdueTasks() {
		List<Task> list = taskService.getOverdueTasks();
		
		return ResponseEntity.ok().body(taskMapper.taskListToTaskResponseDto(list));
	}
	
	@GetMapping("/active/{id}")
	public ResponseEntity<List<TaskResponseDTO>> getActiveTasksByUser(@PathVariable Integer id) {
		List<Task> list = taskService.getActiveTasksByUserId(id);
		
		return ResponseEntity.ok().body(taskMapper.taskListToTaskResponseDto(list));
	}
	
	@GetMapping("/active/total")
    public ResponseEntity<?> getTotalActiveTasks() {
		
        return ResponseEntity.ok().body(taskMapper.numberToTaskCountDto(taskService.getTotalActiveTasks()));
    }

    @GetMapping("/active/total/user/{id}")
    public ResponseEntity<?> getTotalActiveTasksByUser(@PathVariable Integer id) {
    	return ResponseEntity.ok().body(taskMapper.numberToTaskCountDto(taskService.getTotalActiveTasksByUser(id)));
    }
	
	@PostMapping
	public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskCreationDTO data) 
			throws URISyntaxException {
		Task task = taskService.createTask(
				taskMapper.taskCreationDtoToTask(data), 
				data.project_id(), 
				data.user_id());
		
		return ResponseEntity.created(new URI("/task/" + task.getId()))
				.body(taskMapper.taskToTaskResponseDto(task));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody @Valid TaskCreationDTO data, @PathVariable Integer id) 
			throws URISyntaxException {
		Task task = taskMapper.taskCreationDtoToTask(data);
		task.setId(id);
		taskService.updateTask(task, data.project_id(), data.user_id());
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) 
			throws URISyntaxException {
		taskService.deleteTaskById(id);
		return ResponseEntity.noContent().build();
	}
}
