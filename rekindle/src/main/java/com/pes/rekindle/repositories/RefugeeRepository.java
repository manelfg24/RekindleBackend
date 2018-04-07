package com.pes.rekindle.repositories;
import java.sql.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pes.rekindle.entities.Refugee;

@Transactional
public interface RefugeeRepository extends Repository<Refugee, String> {
	public Refugee findByMail(String mail);
	public Optional<Refugee> findOptionalByMail(String mail);

	@Modifying
	@Transactional
	@Query(value = "insert into Refugee"
			+ " values(:mail, :password, :name, :surname1, :surname2, :phoneNumber,"
			+ " :birthdate, :sex, :country, :town, :ethnic, :bloodType, :eyeColor)"
			, nativeQuery=true)
	public void save(@Param("mail")String mail, @Param("password")String password, @Param("name")String name, 
			@Param("surname1")String surname1, @Param("surname2")String surname2, @Param("phoneNumber")Integer phoneNumber,
			@Param("birthdate")Date birthdate, @Param("sex")String sex, @Param("country")String country,
			@Param("town")String town, @Param("ethnic")String ethnic, @Param("bloodType")String bloodType,
			@Param("eyeColor")String eyeColor) throws Exception;
}
