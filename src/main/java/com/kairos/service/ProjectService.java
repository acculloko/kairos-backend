package com.kairos.service;

import java.util.List;

import com.kairos.domain.project.Project;

public interface ProjectService {

	public List<Project> getProjects();
	
	public Project findById(Integer id);
	
	public Project createProject(Project project, Integer userId);
	
	public void updateProject(Project project, Integer userId);
	
	public void deleteProjectById(Integer id);
	
}
