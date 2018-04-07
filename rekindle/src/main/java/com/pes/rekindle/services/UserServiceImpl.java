package com.pes.rekindle.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.pes.rekindle.entities.User;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.UserRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	VolunteerRepository volunteerRepository;
	@Autowired
	RefugeeRepository refugeeRepository;
	
	public boolean logIn(String mail, String password) {
		Optional<User> oUser = userRepository.findOptionalByMail(mail);
		boolean correctPassword = false;
		if(oUser.isPresent()) {
			User user =  oUser.get();
			correctPassword = (user.getPassword() == password);
		}
		return correctPassword;
	}
	
	public String createVolunteer(String mail, String password, String name, String surname1, String surname2){
		String creationResult = "Usuario creado con exito";
		try {
			volunteerRepository.save(mail, password, name, surname1, surname2);
		}
		catch (Exception e){
			creationResult = "El usuario ya existe";
		}
		return creationResult;
	}
	
	 /*
	public String createVolunteer(String mail, String password, String name, String surname1, String surname2){
		System.out.println("Mail "+mail+"   Password "+password);
		Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
		String creationResult = "El voluntario ya existe";
		if(!oVolunteer.isPresent()) {
			Volunteer v = new Volunteer();
			v.setMail(mail);
			v.setPassword(password);
			v.setName(name);
			v.setSurname1(surname1);
			v.setSurname2(surname2);
			creationResult = volunteerRepository.save(v);
		}
		return creationResult;
	}
	*/

	@Override
	public String createRefugee(String mail, String password, String name, String surname1, String surname2,
			Integer phoneNumber, Date birthdate, String sex, String country, String town, String ethnic,
			String bloodType, String eyeColor) {
		String creationResult = "Usuario creado con exito";
		try {
			refugeeRepository.save(mail, password, name, surname1, surname2, phoneNumber,birthdate, sex, 
					country, town, ethnic, bloodType, eyeColor);
		}
		catch (Exception e){
			creationResult = "El usuario ya existe";
		}
		return creationResult;
	}	
}
