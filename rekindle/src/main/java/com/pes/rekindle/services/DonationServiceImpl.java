package com.pes.rekindle.services;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.repositories.DonationRepository;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	DonationRepository donationRepository;

	
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



}
