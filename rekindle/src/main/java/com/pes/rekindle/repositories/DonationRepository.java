package com.pes.rekindle.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Donation;

@Transactional
public interface DonationRepository extends Repository<Donation, String> {
	public void save(Donation donation);
	
	public Collection<Donation> findAll();
}