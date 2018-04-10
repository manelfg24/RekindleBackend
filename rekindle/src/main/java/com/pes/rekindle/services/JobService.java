package com.pes.rekindle.services;

public interface JobService {

	String createJob(String name, String mail, Integer phoneNumber, String adress, String charge, String requirements,
			Double hoursDay, Double hoursWeek, Integer duration, Integer places, Double salaray, String description);
}
