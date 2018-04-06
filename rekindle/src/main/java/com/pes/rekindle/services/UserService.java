package com.pes.rekindle.services;

import com.pes.rekindle.entities.User;

public interface UserService {
	
	boolean logIn(String user, String password);
}
