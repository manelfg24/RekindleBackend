package com.pes.rekindle.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Lodge;

@Transactional
public interface LodgeRepository extends Repository<Lodge, String> {
	public void save(Lodge lodge);
}