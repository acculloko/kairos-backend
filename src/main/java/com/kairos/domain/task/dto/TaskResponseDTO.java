package com.kairos.domain.task.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.project.dto.ProjectResponseDTO;
import com.kairos.domain.task.TaskStatus;
import com.kairos.domain.user.dto.UserResponseDTO;

public record TaskResponseDTO(
		Integer id,
		String name, 
		String description, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate start_date,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate end_date, 
		TaskStatus status,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime creation_date,
		UserResponseDTO responsible_user, 
		ProjectResponseDTO project) {

}
