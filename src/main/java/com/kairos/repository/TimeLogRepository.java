package com.kairos.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kairos.domain.timelog.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {

	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(SECOND, t.start_date, t.end_date)), 0) " +
	           "FROM TimeLog t WHERE t.user.id = :userId " +
	           "AND t.start_date >= :startDate AND t.end_date <= :endDate")
	    Long getTotalLoggedSecondsByUserByPeriod(
	        @Param("userId") Integer userId,
	        @Param("startDate") LocalDateTime startDate,
	        @Param("endDate") LocalDateTime endDate
	    );
	
	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(SECOND, t.start_date, t.end_date)), 0) " +
	           "FROM TimeLog t WHERE t.user.id = :userId")
	    Long getTotalLoggedSecondsByUser(Integer userId);

	@Query("SELECT COALESCE(SUM(TIMESTAMPDIFF(SECOND, t.start_date, t.end_date)), 0) " +
	           "FROM TimeLog t")
	    Long getTotalLoggedSeconds();
	
}
