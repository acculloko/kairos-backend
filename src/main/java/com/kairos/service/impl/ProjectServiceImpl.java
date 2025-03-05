package com.kairos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kairos.domain.project.Project;
import com.kairos.repository.ProjectRepository;
import com.kairos.service.ProjectService;
import com.kairos.service.UserService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project findById(Integer id) {
		Optional<Project> project = projectRepository.findById(id);
		return project.orElse(null);
	}

	@Override
	public Project createProject(Project project, Integer userId) {
		project.setResponsible_user(null);
		
		if (userId != null) {
			project.setResponsible_user(userService.findById(userId));
		}
		
		return projectRepository.save(project);
	}

	@Override
	public void updateProject(Project project, Integer userId) {
		Project currentProject = this.findById(project.getId());
		
		currentProject.setName(project.getName());
		currentProject.setDescription(project.getDescription());
		currentProject.setStart_date(project.getStart_date());
		currentProject.setEnd_date(project.getEnd_date());
		currentProject.setStatus(project.getStatus());
		currentProject.setResponsible_user(null);
		currentProject.setPriority(project.getPriority());
		
		if (userId != null) {
			currentProject.setResponsible_user(userService.findById(userId));
		}
		
		projectRepository.save(currentProject);
	}

	@Override
	public void deleteProjectById(Integer id) {
		projectRepository.deleteById(id);
		
	}

}
