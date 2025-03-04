package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
