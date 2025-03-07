package com.kairos.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.domain.user.User;
import com.kairos.domain.user.dto.UserCreationDTO;
import com.kairos.domain.user.dto.UserResponseDTO;
import com.kairos.mapper.UserMapper;
import com.kairos.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getUsers() {
		List<User> list = userService.getUsers();
		List<UserResponseDTO> dtoList = userMapper.userListToUserResponseDto(list);
		
		return ResponseEntity.ok().body(dtoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Integer id) {
		User user = userService.findById(id);
		UserResponseDTO userDto = userMapper.userToUserResponseDto(user);
		
		return ResponseEntity.ok().body(userDto);
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreationDTO data) 
			throws URISyntaxException {
		System.out.println(data.password());
		User user = userService.createUser(userMapper.userCreationDtoToUser(data));
		
		return ResponseEntity.created(new URI("/user/" + user.getId())).body(userMapper.userToUserResponseDto(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody @Valid UserCreationDTO data, @PathVariable Integer id) 
			throws URISyntaxException {
		User user = userMapper.userCreationDtoToUser(data);
		user.setId(id);
		userService.updateUser(user);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) 
			throws URISyntaxException {
		userService.deleteUserById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
