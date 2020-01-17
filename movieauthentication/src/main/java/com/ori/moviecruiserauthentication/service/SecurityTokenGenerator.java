package com.ori.moviecruiserauthentication.service;

import java.util.Map;

import com.ori.moviecruiserauthentication.model.User;

public interface SecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);

}
