package com.kairos.domain.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails{

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
	private UserRole role;
	
	
//	Constructors
	
	public User(String name, String email, String password, UserRole role) {
		this.name = name;
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.role = role;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		} else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}


	@Override
	public String getUsername() {
		return email;
	}
	
}
