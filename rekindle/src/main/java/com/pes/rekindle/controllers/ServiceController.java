package com.pes.rekindle.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.Lodge;
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
	
	@RequestMapping(value="/crearDonacion/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&limite-peticiones={places}&hora-inicio={startTime}"
			+ "&hora-fin={endTime}&descripcion={description}", method=RequestMethod.POST)
	 public String createDonation(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("places")Integer places, @PathVariable("startTime")Time startTime,
		   @PathVariable("endTime")Time endTime, @PathVariable("description")String description) {
				
		String creationResult = serviceService.createDonation(name, mail, phoneNumber, adress, places,
				startTime, endTime, description);
		return creationResult;
	}
	
	// Cambiar prerequisite por requirements
	@RequestMapping(value="/crearCursoEducativo/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&ambito={ambit}&requisitos-previos={prerequisite}"
			+ "&horario={schedule}&plazas-disponibles={places}&precio={price}&descripcion={description}", method=RequestMethod.POST)
	 public String createEducation(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("ambit")String ambit,@PathVariable("prerequisite")String prerequisite,
		   @PathVariable("schedule")String schedule, @PathVariable("places")Integer places, @PathVariable("price")Integer price,
		   @PathVariable("description")String description) {
				
		String creationResult = serviceService.createEducation(name, mail, phoneNumber, adress,ambit, prerequisite, schedule,
				places, price, description);
		return creationResult;
	}	
	
	@RequestMapping(value="/crearOfertaEmpleo/nombre={name}&email={mail}&telefono={phoneNumber}"
			+ "&direccion={adress}&puesto={charge}&requisitos-necesarios={requirements}"
			+ "&jornada={hoursDay}&horas-semanales={hoursWeek}&duracion={duration}&plazas-disponibles={places}"
			+ "&sueldo={salary}&descripcion={description}", method=RequestMethod.POST)
	 public String createEducation(@PathVariable("name")String name, @PathVariable("mail")String mail,
		   @PathVariable("phoneNumber")Integer phoneNumber,  @PathVariable("adress")String adress,
		   @PathVariable("charge")String charge,@PathVariable("requirements")String requirements,
		   @PathVariable("hoursDay")Double hoursDay, @PathVariable("hoursWeek")Double hoursWeek, 
		   @PathVariable("duration")Integer duration, @PathVariable("places")Integer places, 
		   @PathVariable("salary")Double salary, @PathVariable("description")String description) {
				
		String creationResult = serviceService.createJob(name, mail, phoneNumber, adress, charge, requirements, hoursDay,
				hoursWeek, duration, places, salary, description);
		return creationResult;
	}	
	
	@RequestMapping(value="/listarServicios", method=RequestMethod.GET)
	 public Map<String, ArrayList<Object>> listServices() {
		Map<String, ArrayList<Object>> result = new HashMap<String, ArrayList<Object>>(); 
		result.put("Servicios", serviceService.listServices());
		return result;
	 }
	
	@RequestMapping(value="/seleccionarServicio/id={id}&tipo-servicio={serviceType}", method=RequestMethod.POST)
	 public Object infoService(@PathVariable("id")Long id, @PathVariable("serviceType")char serviceType) {
		Object service = serviceService.infoService(id, serviceType);
		return service;
	 }	
}