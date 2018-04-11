package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VolunteerRepositoryTest {
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	private Volunteer volunteer;
	
	@Before
	public void init() {
		volunteer = new Volunteer();
		volunteer.setMail("alex@gmail.com");
		volunteer.setPassword("1234");
		volunteer.setName("alex");
		volunteer.setSurname1("sanchez");
	}

	@Test
	public void saveVolunteerTest() {
		volunteerRepository.save(volunteer);
		assertEquals("alex@gmail.com", volunteerRepository.findByMail("alex@gmail.com").getMail());
	}
	
	@Test
	public void findNotExistingVolunteer() {
		Volunteer volunteer = volunteerRepository.findByMail("sanchon@");
		assertNull(volunteer);
	}
	
	@Test
	public void findVolunteerByMailAndPasswordTest() {
		Volunteer volunteer = volunteerRepository.findByMailAndPassword("alex@gmail.com", "1234");
		assertEquals("alex@gmail.com", volunteer.getMail());
	}
}
