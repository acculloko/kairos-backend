package com.kairos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.kairos.domain.user.User;
import com.kairos.domain.user.dto.UserCreationDTO;
import com.kairos.domain.user.dto.UserResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	
	User userCreationDtoToUser(UserCreationDTO dto);
	
	UserResponseDTO userToUserResponseDto(User user);
	
	List<UserResponseDTO> userListToUserResponseDto(List<User> users);
	
}
