
package com.pes.rekindle.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Job;

@Transactional
public interface JobRepository extends Repository<Job, String> {
    public void save(Job job);

    public Collection<Job> findAll();

    public Object findById(Long id);
}
