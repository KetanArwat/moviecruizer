package com.ori.moviecruiserauthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ori.moviecruiserauthentication.exception.UserAlreadyExistsException;
import com.ori.moviecruiserauthentication.exception.UserNotFoundException;
import com.ori.moviecruiserauthentication.model.User;
import com.ori.moviecruiserauthentication.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	
	public UserServiceImplementation(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> existingUser = userRepository.findById(user.getUserId());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User with id already exists");
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if (null == user) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}
}
