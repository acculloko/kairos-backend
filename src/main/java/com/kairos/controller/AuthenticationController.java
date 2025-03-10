package com.kairos.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.domain.user.User;
import com.kairos.domain.user.dto.AuthenticationDTO;
import com.kairos.domain.user.dto.LoginResponseDTO;
import com.kairos.mapper.UserMapper;
import com.kairos.service.UserService;
import com.kairos.service.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
//		Saves last login to database
		userService.lastLogin(data.login());
		
//		User user = userService.findByEmail(data.login());
//		LoginResponseDTO loginResponse = userMapper.userToLoginResponseDto(user, token);
		
		return ResponseEntity.ok(Map.of("token", token));
	}
	
}
