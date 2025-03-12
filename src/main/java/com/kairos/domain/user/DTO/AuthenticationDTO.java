package com.kairos.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
		@NotBlank(message = "Email is required")
		String login, 
		
		@NotBlank(message = "Password is required")
		String password) {

}
