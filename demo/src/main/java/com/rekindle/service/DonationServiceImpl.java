package com.rekindle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rekindle.entities.Donation;
import com.rekindle.repositories.DonationRepository;

@Service
public class DonationServiceImpl implements DonationService {
	
	@Autowired
	DonationRepository donationRepository;
	
	public Iterable<Donation> listAllDonations() {
		return donationRepository.findAll();
	}

}
