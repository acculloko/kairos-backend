package com.kairos.service;

import java.util.List;

import com.kairos.domain.user.User;

public interface UserService {

	public List<User> getUsers();
	
	public User findById(Integer id);
	
	public User findByEmail(String email);
	
	public User createUser(User user);
	
	public void updateUser(User user);
	
	public void lastLogin(String email);
	
	public void deleteUserById(Integer id);
	
}
