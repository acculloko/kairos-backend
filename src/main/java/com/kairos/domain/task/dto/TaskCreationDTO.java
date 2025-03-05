package com.kairos.domain.task.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.task.TaskStatus;

public record TaskCreationDTO(
		Integer project_id, 
		String name, 
		String description, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate start_date,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
		LocalDate end_date, 
		TaskStatus status, 
		Integer user_id) {

}
