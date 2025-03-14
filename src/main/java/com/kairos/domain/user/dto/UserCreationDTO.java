package com.kairos.domain.user.dto;

import com.kairos.domain.user.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreationDTO(
		
		@NotBlank(message = "Name is required")
		String name, 
		
		@NotBlank(message = "Email is required")
		String email, 
		
		@NotBlank(message = "Password is required")
		String password, 
		
		@NotNull(message = "Role is required")
		UserRole role) {

}
