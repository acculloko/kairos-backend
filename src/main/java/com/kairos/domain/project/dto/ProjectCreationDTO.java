package com.kairos.domain.project.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.project.ProjectPriority;
import com.kairos.domain.project.ProjectStatus;

public record ProjectCreationDTO(
		String name, 
		String description, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate start_date, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate end_date, 
		ProjectStatus status, 
		Integer user_id, 
		ProjectPriority priority
		) {

}
