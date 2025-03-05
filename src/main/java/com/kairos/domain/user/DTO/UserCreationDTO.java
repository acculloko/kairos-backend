package com.kairos.domain.user.dto;

import com.kairos.domain.user.UserRole;

public record UserCreationDTO(String name, String email, String password, UserRole role) {

}
