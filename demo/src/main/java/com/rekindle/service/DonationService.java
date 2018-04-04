package com.rekindle.service;

import com.rekindle.entities.Donation;

public interface DonationService {
	
	Iterable<Donation> listAllDonations();
	
}
