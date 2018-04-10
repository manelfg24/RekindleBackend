package com.pes.rekindle.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.repositories.LodgeRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	LodgeRepository lodgeRepository;

	
	 public String createLodge(String name, String mail, Integer phoneNumber, String adress, Integer places, 
			 Date dateLimit, String description) {
	  System.out.println("hola");
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
}
