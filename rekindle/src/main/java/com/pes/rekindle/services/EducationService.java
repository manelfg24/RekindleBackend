package com.pes.rekindle.services;

import java.sql.Time;

public interface EducationService {

	String createEducation(String name, String mail, Integer phoneNumber, String adress, String ambit,
			String prerequisite, String schedule, Integer places, Integer price, String description);

	
	
	
}
