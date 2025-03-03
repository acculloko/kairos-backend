package com.kairos.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

//	test
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "email", unique = true)
	@NotBlank
	@Email
	private String email;
	
	@Column(name = "password")
	@NotBlank
	@Size(min = 4)
	private String password;
	
	@Column(name = "creation_date", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@Column(name = "last_login")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
//	@UpdateTimestamp
	private LocalDateTime last_login;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
