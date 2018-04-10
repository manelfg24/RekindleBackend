package com.pes.rekindle.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.services.ServiceService;

@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;

	@RequestMapping(value="/crearAlojamiento/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&limite-peticiones={places}&fecha-limite={dateLimit}"
			+ "&descripcion={description}", method=RequestMethod.POST)
	 public String createLodge(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("places")Integer places, @PathVariable("dateLimit")Date dateLimit,
		   @PathVariable("description")String description) {
		
		System.out.println(name + " " + mail + " " + phoneNumber);
		
		String creationResult = serviceService.createLodge(name, mail, phoneNumber, adress, places, 
				dateLimit, description);
		return creationResult;
	 }	
}
