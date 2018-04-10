package com.pes.rekindle.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.User;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.VolunteerRepository;
import com.pes.rekindle.services.UserService;

@RestController
//@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/test1/nombre={name}&email={mail}", method=RequestMethod.GET)
	public String test1(@PathVariable("name")String name, @PathVariable("mail")String mail) {
		return "Nombre "+name+"   Mail: "+mail;
	}	
	
	@RequestMapping(value="/test2"/*, method=RequestMethod.GET*/)
	public int test2() {
		return 1;
	}
	
	@RequestMapping(value="/test3", method=RequestMethod.GET)
	public Object test3() {
		String mail = "Fernando@fib";
		String password = "12345"; 
		Object user = userService.logInRefugee(mail, password);
		return user;
	}
	
	
	@RequestMapping(value="/login/email={mail}&password={password}", method=RequestMethod.GET)
	public Object logIn(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		Object user = userService.logIn(mail, password);
		return user;
	}	
	
	@RequestMapping(value="/changePasswordVolunteer/email={mail}&password={password}", method=RequestMethod.POST)
	public void changePasswordVolunteer(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		userService.changePasswordVolunteer(mail, password);
	}	
	
	@RequestMapping(value="/changePasswordRefugee/email={mail}&password={password}", method=RequestMethod.POST)
	public void changePasswordRefugee(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		userService.changePasswordRefugee(mail, password);
	}

	@RequestMapping(value="/registrarVoluntario/nombre={name}&email={mail}&password={password}&"
						+ "apellido1={surname1}&apellido2={surname2}", method=RequestMethod.POST)
	public String createVolunteer(@PathVariable("name")String name, @PathVariable("mail")String mail, @PathVariable("password")String password,
						  @PathVariable("surname1")String surname1, @PathVariable("surname2")String surname2) {
		String creationResult = userService.createVolunteer(mail, password, name, surname1, surname2);
		return creationResult;
	}	
	
	@RequestMapping(value="/registrarRefugiado/nombre={name}&email={mail}&password={password}&apellido1={surname1}&apellido2={surname2}"
			+ "&telefono={phoneNumber}&nacimiento={birthdate}&sexo={sex}&pais={country}&pueblo={town}&etnia={ethnic}&gs={bloodType}&color_ojos={eyeColor}", 
			method=RequestMethod.POST)
	public String createRefugee(@PathVariable("mail")String mail, @PathVariable("password")String password, @PathVariable("name")String name, 
			@PathVariable("surname1")String surname1, @PathVariable("surname2")String surname2, @PathVariable("phoneNumber")Integer phoneNumber,
			@PathVariable("birthdate")Date birthdate, @PathVariable("sex")String sex, @PathVariable("country")String country,
			@PathVariable("town")String town, @PathVariable("ethnic")String ethnic, @PathVariable("bloodType")String bloodType,
			@PathVariable("eyeColor")String eyeColor) {
		String creationResult = userService.createRefugee(mail, password, name, surname1, surname2, phoneNumber,
				birthdate, sex, country, town, ethnic, bloodType, eyeColor);
		return creationResult;		
	}
	
	/*
	@RequestMapping(value="/create/{mail}/{password}", method=RequestMethod.GET)
	public boolean create(@PathVariable("mail")String mail, @PathVariable("password")String password) {
		boolean creationResult = userService.createUser(mail, password);
		return creationResult;
	}	
	*/	
	
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
