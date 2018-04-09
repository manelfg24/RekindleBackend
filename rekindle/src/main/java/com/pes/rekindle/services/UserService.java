package com.pes.rekindle.services;

import java.sql.Date;

import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.User;
import com.pes.rekindle.entities.Volunteer;

public interface UserService {
	
	//boolean logIn(String user, String password);
	Object logIn(String user, String password);
	
	String createVolunteer(String mail, String password, String name, String surname1, String surname2);
	
	String createRefugee(String mail, String password, String name, String surname1, String surname2,
			Integer phoneNumber, Date birthdate, String sex, String country, String town, String ethnic,
			String bloodType, String eyeColor);
	
	Volunteer logInVolunteer(String mail, String password);
	Refugee logInRefugee(String mail, String password);
	
	void changePasswordVolunteer(String mail, String password);
	void changePasswordRefugee(String mail, String password);
	
}