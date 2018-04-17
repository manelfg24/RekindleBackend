package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobRepositoryTest {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	private Job job;
	
	@Before
	public void init() {
		
		job = new Job();
		job.setId((long) 1);
		job.setServiceType('j');
		job.setName("Especialista en residuos");
		job.setPhoneNumber(684342382);
		job.setAdress("C/Barcelona");
		job.setRequirements("Superar pruebas ayuntamiento Sabadell");
		job.setContractDuration(3);
		job.setPlaces(5);
		job.setCharge("Basurero");
		job.setHoursDay(12);
		job.setDescription("Ayuda a mantener las calles de Sabadell limpias");
		job.setSalary(500);
		job.setHoursWeek(60);
		
		Volunteer volunteer = new Volunteer();
		volunteer.setMail("alex@gmail.com");
		volunteer.setPassword("1234");
		volunteer.setName("alex");
		volunteer.setSurname1("sanchez");
		volunteerRepository.save(volunteer);
		
		job.setVolunteer("alex@gmail.com");
	}
	
	@Test
	public void saveJobTest() {
		jobRepository.save(job);
		Job foundJob = (Job) jobRepository.findById((long) 1);
		assertEquals((long) 1, foundJob.getId());
		assertEquals('j', foundJob.getServiceType());
		assertEquals("Especialista en residuos", foundJob.getName());
		assertEquals(684342382, (int) foundJob.getPhoneNumber());
		assertEquals("C/Barcelona", foundJob.getAdress());
		assertEquals(3, (int) foundJob.getContractDuration());
		assertEquals("Superar pruebas ayuntamiento Sabadell", foundJob.getRequirements());
		assertEquals(500, (int) foundJob.getSalary());
		assertEquals(60, (int) foundJob.getHoursWeek());
		assertEquals(12, (int) foundJob.getHoursDay());
		assertEquals("Basurero", foundJob.getCharge());
		assertEquals("Ayuda a mantener las calles de Sabadell limpias", foundJob.getDescription());
		assertEquals("alex@gmail.com", foundJob.getVolunteer());
	}
	
	@Test
	public void findAllTest() {
		ArrayList<Job> jobServices = (ArrayList<Job>) jobRepository.findAll();
		int i = 0;
		boolean trobat = false;
		while(i < jobServices.size() && !trobat) {
			if(jobServices.get(i).getId() == job.getId()) {
				trobat = true;
			}
		}
		assertTrue(trobat);
	}
	
}
