package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.kairos.domain.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	UserDetails findByEmail(String email);
	
	User findUserByEmail(String email);
}
