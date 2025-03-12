package com.kairos.domain.task.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.task.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreationDTO(
		
		Integer project_id, 
		
		@NotBlank(message = "Name is required")
		String name, 
		
		String description, 
		
		@NotNull(message = "Start_date is required")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate start_date,
		
		@NotNull(message = "End_date is required")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate end_date, 
		
		@NotNull(message = "Status is required")
		TaskStatus status, 
		
		Integer user_id) {

}
