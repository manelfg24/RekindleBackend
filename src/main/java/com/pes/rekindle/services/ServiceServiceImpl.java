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
	

	@Override
	public void createLodge(String name, String mail, Integer phoneNumber, String adress, Integer places,
			java.util.Date date, String description) {
		Lodge lodge = new Lodge();
		  lodge.setName(name);
		  lodge.setVolunteer(mail);
		  lodge.setServiceType('l');
		  lodge.setPhoneNumber(phoneNumber);
		  lodge.setAdress(adress);
		  lodge.setPlaces(places);
		  lodge.setDateLimit(date);
		  lodge.setDescription(description);
		  lodgeRepository.save(lodge);
	}
	/*
	 public Boolean createLodge(String name, String mail, Integer phoneNumber, String adress, Integer places, 
			 Date dateLimit, String description) {
	  Lodge lodge = new Lodge();
	  lodge.setName(name);
	  lodge.setVolunteer(mail);
	  lodge.setServiceType('l');
	  lodge.setPhoneNumber(phoneNumber);
	  lodge.setAdress(adress);
	  lodge.setPlaces(places);
	  lodge.setDateLimit(dateLimit);
	  lodge.setDescription(description);
	  lodgeRepository.save(lodge);
	  return true;
	 }
	*/
		
	 public void createDonation(String name, String mail, Integer phoneNumber, String adress, Integer places, Time startTime,
				Time endTime, String description) {
	  Donation donation = new Donation();
	  donation.setName(name);
	  donation.setVolunteer(mail);
	  donation.setServiceType('d');
	  donation.setPhoneNumber(phoneNumber);
	  donation.setAdress(adress);
	  donation.setPlaces(places);
	  donation.setStartTime(startTime);
	  donation.setEndTime(endTime);
	  donation.setDescription(description);
	  donationRepository.save(donation);
	 }

	public void createEducation(String name, String mail, Integer phoneNumber, String adress, String ambit,
			String requirements, String schedule, Integer places, Integer price, String description) {
		Education education = new Education();
		education.setName(name);
		education.setVolunteer(mail);
		education.setServiceType('e');
		education.setPhoneNumber(phoneNumber);
		education.setAdress(adress);
		education.setAmbit(ambit);
		education.setRequirements(requirements);
		education.setSchedule(schedule);
		education.setPlaces(places);
		education.setDescription(description);
		educationRepository.save(education);
	}
	
	public void createJob(String name, String mail, Integer phoneNumber, String adress, String charge,
			String requirements, Double hoursDay, Double hoursWeek, Integer duration, Integer places, Double salary,
			String description) {
		Job job = new Job();
		job.setName(name);
		job.setVolunteer(mail);
		job.setServiceType('j');
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
		}
	
	
	public ArrayList<Object> listServices() {
		ArrayList<Object> listServices = new ArrayList<Object>();
		listServices.addAll(lodgeRepository.findAll());
		listServices.addAll(educationRepository.findAll());
		listServices.addAll(donationRepository.findAll());
		listServices.addAll(jobRepository.findAll());
		return listServices;
	}

	public Object infoService(Long id, char serviceType) {
		Object service = new Object();
		if (serviceType == 'l') 
			service = lodgeRepository.findById(id);
		else if (serviceType == 'e')
			service = educationRepository.findById(id);
		else if (serviceType == 'j')
			service = jobRepository.findById(id);
		else if (serviceType == 'd')
			service = donationRepository.findById(id);
		else
			service = "Servicio no encontrado";
		return service;		
	}




}
