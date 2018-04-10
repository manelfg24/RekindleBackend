package com.pes.rekindle.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Job;

@Transactional
public interface JobRepository extends Repository<Job, String> {
	public void save(Job job);
}
