package com.pes.rekindle.repositories;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import com.pes.rekindle.entities.Lodge;


@Transactional
public interface LodgeRepository extends Repository<Lodge, String> {
	//public void save(Lodge lodge);
}