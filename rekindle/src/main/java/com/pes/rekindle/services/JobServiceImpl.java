package com.pes.rekindle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobRepository jobRepository;

	@Override
	public String createJob(String name, String mail, Integer phoneNumber, String adress, String charge,
			String requirements, Double hoursDay, Double hoursWeek, Integer duration, Integer places, Double salary,
			String description) {
		Job job = new Job();
		job.setName(name);
		job.setVolunteer(mail);
		job.setPhoneNumber(phoneNumber);
		job.setAdress(adress);
		job.setCharge(charge);
		job.setRequirements(requirements);
		job.setHoursDay(hoursDay);
		job.setHoursWeek(hoursWeek);
		job.setContractDuration(duration);
		job.setPlaces(places);
		job.setSalary(salary);
		job.setDescription(description);
		jobRepository.save(job);
		return "Servicio de empleo creado con exito";
		}
	}

	
	
/*
@Override
public String createJob(String name, String mail, Integer phoneNumber, String adress, String charge,
		String requirements, String hoursDay, Integer hoursWeek, Integer duration, Integer places, Integer salaray,
		String description) {
	Job job = new Job();

	
	return null;
}*/