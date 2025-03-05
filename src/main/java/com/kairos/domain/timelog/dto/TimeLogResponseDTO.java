package com.kairos.domain.timelog.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.task.dto.TaskResponseDTO;
import com.kairos.domain.user.dto.UserResponseDTO;

public record TimeLogResponseDTO(
		Integer id,
		String description,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime start_date,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime end_date,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime log_time,
		TaskResponseDTO task,
		UserResponseDTO user) {

}
