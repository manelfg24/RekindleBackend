package com.pes.rekindle;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.hibernate.exception.GenericJDBCException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.LodgeRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LodgeRepositoryTest {
	@Autowired
	private LodgeRepository lodgeRepository;
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	private Lodge lodge;
	
	@Before
	public void init() {
		lodge = new Lodge();
		lodge.setId(123);
		lodge.setAdress("C/Balmes");
		lodge.setDateLimit(new Date(2018-05-05));
		lodge.setDescription("Description");
		lodge.setName("Barcelona's house");
		lodge.setPhoneNumber(664867797);
		lodge.setPlaces(2);
		
		Volunteer volunteer = new Volunteer();
		volunteer.setMail("alex@gmail.com");
		volunteer.setPassword("1234");
		volunteer.setName("alex");
		volunteer.setSurname1("sanchez");
		volunteerRepository.save(volunteer);
		
		lodge.setVolunteer("alex@gmail.com");
	}
	
}
