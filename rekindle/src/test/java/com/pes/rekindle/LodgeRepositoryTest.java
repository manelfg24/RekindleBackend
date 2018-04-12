package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;

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
		Long id = (long) 1;
		lodge.setId(id);
		lodge.setAdress("C/Balmes");
		lodge.setServiceType('l');
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
	
	@Test
	public void saveLodgeTest() {
		lodgeRepository.save(lodge);
		Long id = (long) 1;
		Lodge findLodge = lodgeRepository.findById(id);
		assertEquals(1, findLodge.getId());
		assertEquals(664867797, (int) findLodge.getPhoneNumber());
		assertEquals('l', findLodge.getServiceType());
		assertEquals("C/Balmes", findLodge.getAdress());
		assertEquals("Description", findLodge.getDescription());
		assertEquals("Barcelona's house", findLodge.getName());
		assertEquals(2, (int) findLodge.getPlaces());
		assertEquals("alex@gmail.com", findLodge.getVolunteer());
	}
	
	@Test
	public void findAllTest() {
		ArrayList<Lodge> lodges = lodgeRepository.findAll();
		int i = 0;
		boolean trobat = false;
		while(i < lodges.size() && !trobat) {
			if(lodges.get(i).getId() == lodge.getId()) {
				trobat = true;
			}
		}
		assertTrue(trobat);
	}
	
}
