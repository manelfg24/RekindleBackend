
package com.pes.rekindle.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Lodge;

@Transactional
public interface LodgeRepository extends Repository<Lodge, String> {
    public void save(Lodge lodge);

    public Set<Object> findAll();

    public Lodge findById(Long id);

	public void deleteById(Long id);

	public Set<Object> findByVolunteer(String mail);
	
	public Set<Object> findByInscriptions_Mail(String mail);
}
