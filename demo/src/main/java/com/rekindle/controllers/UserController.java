package com.rekindle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rekindle.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	

}
