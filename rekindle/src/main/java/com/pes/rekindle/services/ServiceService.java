package com.pes.rekindle.services;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface ServiceService {

	String createLodge(String name, String mail, Integer phoneNumber, String adress,
			Integer places, Date dateLimit, String description);

	ArrayList<Object> listServices();
	
	String createDonation(String name, String mail, Integer phoneNumber, String adress, Integer places, Time startTime,
			Time endTime, String description);
	
	String createEducation(String name, String mail, Integer phoneNumber, String adress, String ambit,
			String requirements, String schedule, Integer places, Integer price, String description);
	
	String createJob(String name, String mail, Integer phoneNumber, String adress, String charge, String requirements,
			Double hoursDay, Double hoursWeek, Integer duration, Integer places, Double salaray, String description);
	
	

}
