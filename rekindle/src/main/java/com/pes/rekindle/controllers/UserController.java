package com.pes.rekindle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.User;
import com.pes.rekindle.services.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/test1", method=RequestMethod.GET)
	public User test1() {
		User u = new User();
		u.setMail("mail");
		u.setUserType('v');
		u.setPassword("pass");
		u.setName("name");
		u.setSurname1("sur1");
		u.setSurname2("sur2");
		return u;
	}	
	
	@RequestMapping(value="/test2"/*, method=RequestMethod.GET*/)
	public int test2() {
		return 1;
	}
	

	@RequestMapping(value="/create/{mail}/{password}", method=RequestMethod.GET)
	public boolean create(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		boolean creationResult = userService.createUser(mail, password);
		return creationResult;
	}	
	
	/*
	@RequestMapping(value="/create/mail={mail}&password={password}", method=RequestMethod.GET)
	public boolean create(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		boolean creationResult = userService.createUser(mail, password);
		return creationResult;
	}	
	*/
	/*
	@RequestMapping(value="/login"+"?mail="+"{mail}"+"&password"+"{password}", method=RequestMethod.POST)
	public boolean logIn(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		return userService.logIn(mail, password);
	}
	*/
}
