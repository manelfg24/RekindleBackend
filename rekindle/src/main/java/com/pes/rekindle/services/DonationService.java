package com.pes.rekindle.services;

import java.sql.Date;
import java.sql.Time;

public interface DonationService {

	String createDonation(String name, String mail, Integer phoneNumber, String adress, Integer places, Time startTime,
			Time endTime, String description);

	
	
}
