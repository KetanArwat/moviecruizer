package com.ori.moviecruiserauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ori.moviecruiserauthentication.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByUserIdAndPassword(String userId, String password);

}
