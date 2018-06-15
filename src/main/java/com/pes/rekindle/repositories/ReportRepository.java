package com.pes.rekindle.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.data.repository.Repository;

import com.pes.rekindle.dto.DTOReport;
import com.pes.rekindle.entities.Report;

@Transactional
public interface ReportRepository extends Repository<Report, String> {

	void save(Report report);

	Set<Report> findAll();

	Report findById(Long id);

	Optional<Report> findOptionalById(long id);

	void deleteById(Long id);
	
}
