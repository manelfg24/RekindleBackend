package com.pes.rekindle.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.User;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.VolunteerRepository;
import com.pes.rekindle.services.ServiceService;
import com.pes.rekindle.services.UserService;

@RestController
public class UserController {
	
	private class LogInInfo {
		String mail;
		String password;
		public String getMail() {
			return mail;
		}
		public String getPassword() {
			return password;
		}
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/registrarVoluntario", method=RequestMethod.POST)
	public void createVolunteer(@RequestBody Volunteer volunteer) {
		String creationResult = userService.createVolunteer(volunteer.getMail(), volunteer.getPassword(), volunteer.getName(),
				volunteer.getSurname1(), volunteer.getSurname2());
		if (creationResult.equals("El usuario ya existe")) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST);
			System.out.println("Bad Request");
		}
		else if (creationResult.equals("Usuario creado con exito")) {
			ResponseEntity.status(HttpStatus.OK);
			System.out.println("Ok");
		}
	}	
	
	@RequestMapping(value="/registrarRefugiado", method=RequestMethod.POST)
	public String createRefugee(@RequestBody Refugee refugee) {
		String creationResult = userService.createRefugee(refugee.getMail(), refugee.getPassword(), refugee.getName(), refugee.getSurname1(),
				refugee.getSurname2(), refugee.getPhoneNumber(), refugee.getBirthdate(), refugee.getSex(), refugee.getCountry(), refugee.getTown(),
				refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor());
		return creationResult;		
	}	
	
	@RequestMapping(value="/inicioSesion", method=RequestMethod.POST)
	public Object logIn(@RequestBody LogInInfo logInInfo) {
		Object user = userService.logIn(logInInfo.getMail(), logInInfo.getPassword());
		return user;
	}	
	
	/*
	//Afegir contraseña vella
	@RequestMapping(value="/cambiarContraseñaVoluntario", method=RequestMethod.POST)
	public void changePasswordVolunteer(@RequestBody LogInInfo logInInfo) {
		userService.changePasswordVolunteer(logInInfo.getMail(), logInInfo.getPassword());
	}	
	
	//Afegir contraseña vella
	@RequestMapping(value="/cambiarContraseñaRefugiado", method=RequestMethod.POST)
	public void changePasswordRefugee(@RequestBody LogInInfo logInInfo) {
		userService.changePasswordRefugee(logInInfo.getMail(), logInInfo.getPassword());
	}	
	
	//Recibimos mail
	@RequestMapping(value="consultarPerfil", method=RequestMethod.POST)
	public void 
	
	//Nos pasan todo el objeto, retorna string 
	@RequestMapping(value="modificarPerfil", method=RequestMethod.POST)
	public void 
	*/
}
