package com.pes.rekindle.services;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	LodgeRepository lodgeRepository;
	@Autowired
	EducationRepository educationRepository;
	@Autowired
	DonationRepository donationRepository;
	@Autowired
	JobRepository jobRepository;


	
	 public String createLodge(String name, String mail, Integer phoneNumber, String adress, Integer places, 
			 Date dateLimit, String description) {
	  Lodge lodge = new Lodge();
	  lodge.setName(name);
	  lodge.setVolunteer(mail);
	  lodge.setPhoneNumber(phoneNumber);
	  lodge.setAdress(adress);
	  lodge.setPlaces(places);
	  lodge.setDateLimit(dateLimit);
	  lodge.setDescription(description);
	  lodgeRepository.save(lodge);
	  return "Servicio de alojamiento creado con exito";
	 }
	
		
	 public String createDonation(String name, String mail, Integer phoneNumber, String adress, Integer places, Time startTime,
				Time endTime, String description) {
	  Donation donation = new Donation();
	  donation.setName(name);
	  donation.setVolunteer(mail);
	  donation.setPhoneNumber(phoneNumber);
	  donation.setAdress(adress);
	  donation.setPlaces(places);
	  donation.setStartTime(startTime);
	  donation.setEndTime(endTime);
	  donation.setDescription(description);
	  donationRepository.save(donation);
	  return "Servicio de donación creado con exito";
	 }

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
	
	
	public ArrayList<Object> listServices() {
		ArrayList<Object> listServices = new ArrayList<Object>();
		listServices.addAll(lodgeRepository.findAll());
		listServices.addAll(educationRepository.findAll());
		listServices.addAll(donationRepository.findAll());
		listServices.addAll(jobRepository.findAll());
		return listServices;
	}
}
