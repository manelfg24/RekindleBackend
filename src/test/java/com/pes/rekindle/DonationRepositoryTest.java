package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DonationRepositoryTest {
	
	@Autowired
	private DonationRepository donationRepository;
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	private Donation donation;
	
	@Before
	public void init() {
		donation = new Donation();
		donation.setId((long) 1);
		donation.setServiceType("Donation");
		donation.setName("Donación de ropa");
		donation.setPhoneNumber(32843479);
		donation.setAdress("C/Aragon");
		donation.setPlaces(5);
		donation.setStartTime(LocalTime.now());
		donation.setEndTime(LocalTime.now());
		donation.setDescription("Donacion de ropa masculina de la talla XL");
		
		Volunteer volunteer = new Volunteer();
		volunteer.setMail("alex@gmail.com");
		volunteer.setPassword("1234");
		volunteer.setName("alex");
		volunteer.setSurname1("sanchez");
		volunteerRepository.save(volunteer);
		
		donation.setVolunteer("alex@gmail.com");
	}
	
	@Test
	public void saveDonationTest() {
		donationRepository.save(donation);
		Donation foundDonation = (Donation) donationRepository.findById((long) 1);
		assertEquals((long) 1, foundDonation.getId());
		assertEquals('d', foundDonation.getServiceType());
		assertEquals("Donación de ropa", foundDonation.getName());
		assertEquals("C/Aragon", foundDonation.getAdress());
		assertEquals(5, (int) foundDonation.getPlaces());
		assertEquals(32843479, (int) foundDonation.getPhoneNumber());
		assertEquals("Donacion de ropa masculina de la talla XL", foundDonation.getDescription());
		assertEquals("alex@gmail.com", foundDonation.getVolunteer());
		assertEquals(5, (int) foundDonation.getPlaces());
	}
	/*
	@Test
	public void findAllTest() {
		ArrayList<Donation> donationServices = (ArrayList<Donation>) donationRepository.findAll();
		int i = 0;
		boolean trobat = false;
		while(i < donationServices.size() && !trobat) {
			if(donationServices.get(i).getId() == donation.getId()) {
				trobat = true;
			}
		}
		assertTrue(trobat);
	}
	*/
}
