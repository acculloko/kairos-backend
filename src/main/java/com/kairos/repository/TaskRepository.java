package com.kairos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kairos.domain.task.Task;
import com.kairos.domain.task.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findAllByProjectId(Integer id);
	
	@Query("SELECT t FROM Task t WHERE t.responsible_user.id = :id")
	List<Task> findAllByResponsibleUserId(@Param("id") Integer id);
	
	@Query("SELECT t FROM Task t WHERE t.end_date < :today AND t.status NOT IN (:excludedStatuses)")
	List<Task> findOverdueTasks(
			@Param("today") LocalDate today, 
			@Param("excludedStatuses") List<TaskStatus> excludedStatuses);
	
	@Query("SELECT t FROM Task t WHERE t.status NOT IN (:excludedStatuses) AND t.responsible_user.id = :userId ORDER BY t.end_date ASC")
	List<Task> findAllExceptDoneAndCancelledByUser(@Param("excludedStatuses") List<TaskStatus> excludedStatuses, 
	                                               @Param("userId") Integer userId);
	
	@Query("SELECT COUNT(t) FROM Task t WHERE t.status NOT IN ('DONE', 'CANCELLED')")
    Long countTasksNotDoneOrCancelled();
	
	@Query("SELECT COUNT(t) FROM Task t WHERE t.responsible_user.id = :userId AND t.status NOT IN ('DONE', 'CANCELLED')")
	Long countTasksNotDoneOrCancelledByUser(Integer userId);
	
}
