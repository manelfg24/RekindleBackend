package com.pes.rekindle.controllers;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.pes.rekindle.services.*;

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
	@Autowired
	private DonationService donationService;
	@RequestMapping(value="/crearDonacion/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&limite-peticiones={places}&hora-inicio={startTime}"
			+ "&hora-fin={endTime}&descripcion={description}", method=RequestMethod.POST)
	 public String createDonation(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("places")Integer places, @PathVariable("startTime")Time startTime,
		   @PathVariable("endTime")Time endTime, @PathVariable("description")String description) {
				
		String creationResult = donationService.createDonation(name, mail, phoneNumber, adress, places,
				startTime, endTime, description);
		return creationResult;
	}
	@Autowired
	private EducationService educationService;
	@RequestMapping(value="/crearCursoEducativo/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&ambito={ambit}&requisitos-previos={prerequisite}"
			+ "&horario={schedule}&plazas-disponibles={places}&precio={price}&descripcion={description}", method=RequestMethod.POST)
	 public String createEducation(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("ambit")String ambit,@PathVariable("prerequisite")String prerequisite,
		   @PathVariable("schedule")String schedule, @PathVariable("places")Integer places, @PathVariable("price")Integer price,
		   @PathVariable("description")String description) {
				
		String creationResult = educationService.createEducation(name, mail, phoneNumber, adress,ambit, prerequisite, schedule,
				places, price, description);
		return creationResult;
	}
}
