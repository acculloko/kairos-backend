package com.kairos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kairos.domain.user.User;
import com.kairos.repository.ProjectRepository;
import com.kairos.repository.UserRepository;
import com.kairos.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private ProjectRepository projectRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElse(null);
	}
	
	@Override
	public User findByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		return user;
	}

	@Override
	public User createUser(User user) {
		User currentUser = new User(
				user.getName(), 
				user.getEmail(), 
				user.getPassword(),
				user.getRole());
		currentUser.setId(null);
		return userRepository.save(currentUser);
	}

	@Override
	public void updateUser(User user) {
		User currentUser = this.findById(user.getId());
		
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		currentUser.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
		currentUser.setRole(user.getRole());
		
		userRepository.save(currentUser);
	}

	@Override
	public void lastLogin(String email) {
		User currentUser = this.findByEmail(email);
		
		currentUser.setLast_login(LocalDateTime.now());
		
		userRepository.save(currentUser);
	}

	@Override
	public void deleteUserById(Integer id) {
		
		userRepository.deleteById(id);
	}
	
}
