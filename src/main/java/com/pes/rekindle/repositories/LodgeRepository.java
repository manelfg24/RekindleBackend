
package com.pes.rekindle.repositories;

import java.util.ArrayList;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Lodge;

@Transactional
public interface LodgeRepository extends Repository<Lodge, String> {
    public void save(Lodge lodge);

    public Set<Lodge> findAll();

    public Object findById(Long id);

	public void deleteById(Long id);
    
    
}
