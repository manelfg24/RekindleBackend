package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

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

import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EducationRepositoryTest {
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	private Education education;
	
	@Before
	public void init() {
		education = new Education();
		education.setId((long) 1);
		education.setServiceType("Education");
		education.setName("Curso español avanzado");
		education.setPhoneNumber(688342393);
		education.setAdress("C/Pare Sallares");
		education.setAmbit("Idiomas");
		education.setRequirements("Nivel básico español e inglés");
		education.setPlaces(5);
		education.setPrice(75);
		education.setSchedule("9-14");
		education.setDescription("Curso de español");
		
		Volunteer volunteer = new Volunteer();
		volunteer.setMail("alex@gmail.com");
		volunteer.setPassword("1234");
		volunteer.setName("alex");
		volunteer.setSurname1("sanchez");
		volunteerRepository.save(volunteer);
		
		education.setVolunteer("alex@gmail.com");
	}
	
	@Test
	public void saveEducationTest() {
		educationRepository.save(education);
		Education foundEducation = (Education) educationRepository.findById((long) 1);
		assertEquals((long) 1, foundEducation.getId());
		assertEquals('e', foundEducation.getServiceType());
		assertEquals("Curso español avanzado", foundEducation.getName());
		assertEquals(688342393, (int) foundEducation.getPhoneNumber());
		assertEquals("C/Pare Sallares", foundEducation.getAdress());
		assertEquals("Idiomas", foundEducation.getAmbit());
		assertEquals("Nivel básico español e inglés", foundEducation.getRequirements());
		assertEquals(5, (int) foundEducation.getPlaces());
		assertEquals(75, (int) foundEducation.getPrice());
		assertEquals("9-14", foundEducation.getSchedule());
		assertEquals("Curso de español", foundEducation.getDescription());
		assertEquals("alex@gmail.com", foundEducation.getVolunteer());
	}
	/*
	@Test
	public void findAllTest() {
		ArrayList<Education> educationServices = (ArrayList<Education>) educationRepository.findAll();
		int i = 0;
		boolean trobat = false;
		while(i < educationServices.size() && !trobat) {
			if(educationServices.get(i).getId() == education.getId()) {
				trobat = true;
			}
		}
		assertTrue(trobat);
	}
	*/
}
