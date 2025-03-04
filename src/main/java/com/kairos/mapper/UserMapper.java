package com.kairos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.kairos.domain.user.User;
import com.kairos.domain.user.DTO.UserCreationDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	
	User userCreationDtoToUser(UserCreationDTO dto);
	
}
