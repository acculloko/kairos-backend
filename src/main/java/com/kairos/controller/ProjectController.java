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

import com.kairos.domain.project.Project;
import com.kairos.domain.project.dto.ProjectCreationDTO;
import com.kairos.domain.project.dto.ProjectResponseDTO;
import com.kairos.mapper.ProjectMapper;
import com.kairos.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@GetMapping
	public ResponseEntity<List<ProjectResponseDTO>> getProjects() {
		List<Project> list = projectService.getProjects();
		
		return ResponseEntity.ok().body(projectMapper.projectListToProjectResponseDto(list));
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<ProjectResponseDTO>> getActiveProjects() {
		List<Project> list = projectService.getActiveProjects();
		
		return ResponseEntity.ok().body(projectMapper.projectListToProjectResponseDto(list));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponseDTO> findUserById(@PathVariable Integer id) {
		Project project = projectService.findById(id);
		
		return ResponseEntity.ok().body(projectMapper.projectToProjectResponseDto(project));
	}
	
	@PostMapping
	public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody @Valid ProjectCreationDTO data) 
			throws URISyntaxException {
		Project project = projectService.createProject(projectMapper.projectCreationDtoToProject(data), data.user_id());
		
		return ResponseEntity.created(new URI("/project/" + project.getId())).body(projectMapper.projectToProjectResponseDto(project));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@RequestBody @Valid ProjectCreationDTO data, @PathVariable Integer id) 
			throws URISyntaxException {
		Project project = projectMapper.projectCreationDtoToProject(data);
		project.setId(id);
		projectService.updateProject(project, data.user_id());
		
		return ResponseEntity.noContent().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Integer id) 
			throws URISyntaxException {
		projectService.deleteProjectById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
