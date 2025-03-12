package com.kairos.domain.timelog.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TimeLogCreationDTO(
		
		@NotNull(message = "Task ID cannot be null")
		Integer task_id,
		
		@NotNull(message = "User ID cannot be null")
		Integer user_id,
		
		@NotBlank(message = "Description cannot be blank")
		String description,
		
		@NotNull(message = "Start_date cannot be null")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime start_date,
		
		@NotNull(message = "End_date cannot be null")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime end_date) {

}
