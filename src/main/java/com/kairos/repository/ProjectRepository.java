package com.kairos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kairos.domain.project.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	@Query("SELECT p FROM Project p WHERE p.status NOT IN ('CANCELLED', 'COMPLETED')")
    List<Project> findAllActiveProjects();

//	boolean existsByResponsibleUserId(Integer userId);
	
}
