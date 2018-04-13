package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.repositories.RefugeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefugeeRepositoryTest {
	
	@Autowired
	private RefugeeRepository refugeeRepository;
	
	private Refugee refugee;
	
	@Before
	public void init() {
		refugee = new Refugee();
		refugee.setMail("fael@gmail.com");
		refugee.setPassword("1234");
		refugee.setName("fael");
		refugee.setSurname1("poxo");
		refugee.setPhoneNumber(686934834);
		refugee.setSex("masculino");
		refugee.setCountry("Kenia");
		refugee.setTown("Narok");
		refugee.setBloodType("B+");
		refugee.setEthnic("Massai Mara");
		refugee.setEyeColor("Marron");
	}

	@Test
	public void saveRefugeeTest() {
		refugeeRepository.save(refugee);
		Refugee refugee = refugeeRepository.findByMail("fael@gmail.com");
		assertEquals("fael@gmail.com", refugee.getMail());
		assertEquals("1234", refugee.getPassword());
		assertEquals("fael", refugee.getName());
		assertEquals("poxo", refugee.getSurname1());
		assertEquals(686934834, (int) refugee.getPhoneNumber());
		assertEquals("masculino", refugee.getSex());
		assertEquals("Kenia", refugee.getCountry());
		assertEquals("Narok", refugee.getTown());
		assertEquals("B+", refugee.getBloodType());
		assertEquals("Massai Mara", refugee.getEthnic());
		assertEquals("Marron", refugee.getEyeColor());
	}
	
	@Test
	public void findNotExistingRefugee() {
		Refugee refugee = refugeeRepository.findByMail("noexiste@");
		assertNull(refugee);
	}
	
	@Test
	public void findRefugeeByMailAndPasswordTest() {
		Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword("alex@gmail.com", "1234");
		if(oRefugee.isPresent()) {
			Refugee refugee = oRefugee.get();
			assertEquals("alex@gmail.com", refugee.getMail());
			assertEquals("1234", refugee.getPassword());
			assertEquals("alex", refugee.getName());
			assertEquals("sanchez", refugee.getSurname1());
			assertEquals(686934834, (int) refugee.getPhoneNumber());
			assertEquals("masculino", refugee.getSex());
			assertEquals("Kenia", refugee.getCountry());
			assertEquals("Narok", refugee.getTown());
			assertEquals("B+", refugee.getBloodType());
			assertEquals("Massai Mara", refugee.getEthnic());
			assertEquals("Marron", refugee.getEyeColor());
		}

	}
}
