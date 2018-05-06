
package com.pes.rekindle.repositories;

import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Job;

@Transactional
public interface JobRepository extends Repository<Job, String> {
    public void save(Job job);

    public Set<Object> findAll();

    public Job findById(Long id);

	public Set<Object> findByVolunteer(String mail);
	
	public Set<Object> findByInscriptions_Mail(String mail);

}
