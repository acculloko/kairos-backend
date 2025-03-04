package com.kairos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.domain.user.User;
import com.kairos.domain.user.DTO.AuthenticationDTO;
import com.kairos.domain.user.DTO.LoginResponseDTO;
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

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
//		Saves last login to database
		userService.lastLogin(data.login());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
}
