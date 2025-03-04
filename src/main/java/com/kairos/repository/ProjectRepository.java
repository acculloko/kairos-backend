package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.domain.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

//	boolean existsByResponsibleUserId(Integer userId);
	
}
