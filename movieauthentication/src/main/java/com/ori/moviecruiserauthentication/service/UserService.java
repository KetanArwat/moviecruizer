package com.ori.moviecruiserauthentication.service;

import com.ori.moviecruiserauthentication.exception.UserAlreadyExistsException;
import com.ori.moviecruiserauthentication.exception.UserNotFoundException;
import com.ori.moviecruiserauthentication.model.User;

public interface UserService {

	boolean saveUser(User user)throws UserAlreadyExistsException;
	
	public User findByUserIdAndPassword(String userId, String password)throws UserNotFoundException;
	
}
