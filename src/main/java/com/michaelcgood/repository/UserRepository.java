package com.michaelcgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michaelcgood.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User findById(String id);
}
