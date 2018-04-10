package com.pes.rekindle.services;

import java.sql.Date;

public interface ServiceService {

	String createLodge(String name, String mail, Integer phoneNumber, String adress,
			Integer places, Date dateLimit, String description);
	
}
