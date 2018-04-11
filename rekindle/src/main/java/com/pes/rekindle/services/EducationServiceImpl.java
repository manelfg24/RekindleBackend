package com.pes.rekindle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Education;
import com.pes.rekindle.repositories.EducationRepository;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	EducationRepository educationRepository;

	@Override
	public String createEducation(String name, String mail, Integer phoneNumber, String adress, String ambit,
			String requirements, String schedule, Integer places, Integer price, String description) {
		Education education = new Education();
		education.setName(name);
		education.setVolunteer(mail);
		education.setPhoneNumber(phoneNumber);
		education.setAdress(adress);
		education.setAmbit(ambit);
		education.setRequirements(requirements);
		education.setSchedule(schedule);
		education.setPlaces(places);
		education.setDescription(description);
		educationRepository.save(education);
		return "Servicio de educación creado con exito";
		 }
	}

	
	




