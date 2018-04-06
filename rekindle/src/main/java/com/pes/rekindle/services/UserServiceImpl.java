package com.pes.rekindle.services;

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
import com.pes.rekindle.entities.User;
import com.pes.rekindle.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public boolean logIn(String mail, String password) {
		Optional<User> oUser = userRepository.findOptionalByMail(mail);
		boolean correctPassword = false;
		if(oUser.isPresent()) {
			User user =  oUser.get();
			correctPassword = (user.getPassword() == password);
		}
		return correctPassword;
	}
	
	public boolean createUser(String mail, String password) {
		System.out.println("Mail "+mail+"   Password "+password);
		Optional<User> oUser = userRepository.findOptionalByMail(mail);
		boolean notExists = false;
		if(!oUser.isPresent()) {
			notExists = true;
			User u = new User();
			u.setMail(mail);
			u.setUserType('v');
			u.setPassword(password);
			u.setName("name");
			u.setSurname1("sur1");
			u.setSurname2("sur2");
			userRepository.save(u);
		}
		else 
			System.out.println("Ya existe");
		return notExists;
	}	
}
