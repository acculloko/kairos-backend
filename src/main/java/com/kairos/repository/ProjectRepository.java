package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
