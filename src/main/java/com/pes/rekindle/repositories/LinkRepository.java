
package com.pes.rekindle.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Link;

@Transactional
public interface LinkRepository extends Repository<Link, String> {

	void save(Link link);

	Set<Link> findAll();

	Link findById(long id);

	void deleteById(Long id);
		
}
