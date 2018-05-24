
package com.pes.rekindle.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Job;

@Transactional
public interface JobRepository extends Repository<Job, String> {
    public void save(Job job);

    public Set<Job> findAll();

    public Job findById(Long id);

    public Set<Job> findByVolunteer(String mail);

    public Set<Job> findByInscriptions_Mail(String mail);

	public void deleteById(long id);

}
