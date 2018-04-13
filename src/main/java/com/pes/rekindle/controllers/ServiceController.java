package com.pes.rekindle.controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.controllers.UserController.LogInInfo;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.services.*;

@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(value="/test2")
		public int test2() {
			return 1;
		}
	
	@RequestMapping(value="/crearAlojamiento", method=RequestMethod.POST)
	public ResponseEntity<String> createLodge(@RequestBody Lodge lodge) {
			serviceService.createLodge(lodge.getName(), lodge.getVolunteer(), lodge.getPhoneNumber(),
				lodge.getAdress(), lodge.getPlaces(), lodge.getDateLimit(),
				lodge.getDescription());
			return ResponseEntity.status(HttpStatus.OK).body(null);
		
	}
	
	@RequestMapping(value="/crearDonacion", method=RequestMethod.POST)
	public ResponseEntity<String> createDonation(@RequestBody Donation donation) {
				serviceService.createDonation(donation.getName(), donation.getVolunteer(),
					donation.getPhoneNumber(),donation.getAdress(),donation.getPlaces(),
					donation.getStartTime(), donation.getEndTime(), donation.getDescription());
				return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value="/crearCursoEducativo", method=RequestMethod.POST)
	public ResponseEntity<String> createEducation(@RequestBody Education education) {
				serviceService.createEducation(education.getName(), education.getVolunteer(),
					education.getPhoneNumber(),education.getAdress(),education.getAmbit(),
					education.getRequirements(), education.getSchedule(),education.getPlaces(),
					education.getPrice(), education.getDescription());
				return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	// Cambiar prerequisite por requirements
	/*@RequestMapping(value="/crearCursoEducativo/nombre={name}&email={mail}&telefono={phoneNumber}"
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
	}	*/
	
	@RequestMapping(value="/crearOfertaEmpleo", method=RequestMethod.POST)
	public ResponseEntity<String> createJob(@RequestBody Job job) {
				serviceService.createJob(job.getName(), job.getVolunteer(),
						job.getPhoneNumber(),job.getAdress(),job.getCharge(),job.getRequirements(),
						job.getHoursDay(),job.getHoursWeek(),job.getContractDuration(),job.getPlaces(),
						job.getSalary(), job.getDescription());
				return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	
/*	@RequestMapping(value="/crearOfertaEmpleo/nombre={name}&email={mail}&telefono={phoneNumber}"
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
	}	*/
	
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