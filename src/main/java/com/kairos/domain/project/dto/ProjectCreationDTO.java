package com.kairos.domain.project.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.project.ProjectPriority;
import com.kairos.domain.project.ProjectStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectCreationDTO(
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
		ProjectStatus status, 
		
		Integer user_id, 
		
		@NotNull(message = "Priority is required")
		ProjectPriority priority
		) {

}
