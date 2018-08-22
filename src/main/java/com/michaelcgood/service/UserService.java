package com.michaelcgood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michaelcgood.model.User;
import com.michaelcgood.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public User findById(String id) {
		return userRepository.findById(id);
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
	public void updateUser(User user) {
		userRepository.save(user);
	}
}
