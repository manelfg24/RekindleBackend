package com.pes.rekindle.services;

import java.sql.Date;
import java.util.ArrayList;

public interface ServiceService {

	String createLodge(String name, String mail, Integer phoneNumber, String adress,
			Integer places, Date dateLimit, String description);

	ArrayList<Object> listServices();
	
}
