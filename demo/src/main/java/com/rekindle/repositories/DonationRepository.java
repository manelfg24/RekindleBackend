package com.rekindle.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.rekindle.entities.Donation;

@Transactional
public interface DonationRepository extends CrudRepository<Donation, Integer> {
}
