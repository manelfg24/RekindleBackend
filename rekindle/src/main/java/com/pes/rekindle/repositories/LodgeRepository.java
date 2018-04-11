package com.pes.rekindle.repositories;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Lodge;

@Transactional
public interface LodgeRepository extends Repository<Lodge, String> {
	public void save(Lodge lodge);
	
	public ArrayList<Lodge> findAll();

	public Object findById(Long id);
}