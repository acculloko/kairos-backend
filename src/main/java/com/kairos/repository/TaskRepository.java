package com.kairos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kairos.domain.task.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findAllByProjectId(Integer id);
	
	@Query("SELECT t FROM Task t WHERE t.responsible_user.id = :id")
	List<Task> findAllByResponsibleUserId(@Param("id") Integer id);
	
}
