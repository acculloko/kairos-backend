package com.kairos.domain.project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.project.ProjectPriority;
import com.kairos.domain.project.ProjectStatus;
import com.kairos.domain.user.dto.UserResponseDTO;

public record ProjectResponseDTO(
		Integer id,
		String name, 
		String description, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate start_date, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate end_date, 
		ProjectStatus status, 
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime creation_date,
		ProjectPriority priority, 
		UserResponseDTO responsible_user) {

}
