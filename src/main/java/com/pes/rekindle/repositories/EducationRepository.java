
package com.pes.rekindle.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Education;

@Transactional
public interface EducationRepository extends Repository<Education, String> {
    public void save(Education education);

    public Collection<Education> findAll();

    public Object findById(Long id);
}
