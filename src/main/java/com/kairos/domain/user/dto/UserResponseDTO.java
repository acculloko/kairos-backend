package com.kairos.domain.user.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.user.UserRole;

public record UserResponseDTO(
		Integer id, 
		String name, 
		String email, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime creation_date, 
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
		LocalDateTime last_login, 
		UserRole role) {

}
