package com.rekindle.service;

import com.rekindle.entities.User;

public interface UserService {
	
	boolean logIn(String user, String password);
}
