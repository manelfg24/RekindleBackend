
package com.pes.rekindle.repositories;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

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

    public void save(Refugee refugee);

    public Set<Refugee> findByName(String name);

    public Set<Refugee> findAll();

    @Modifying
    @Transactional
    @Query(value = "insert into Refugee"
            + " values(:mail, :password, :name, :surname1, :surname2, :phoneNumber,"
            + " :birthdate, :sex, :country, :town, :ethnic, :bloodType, :eyeColor, :biography)", nativeQuery = true)
    public void create(@Param("mail") String mail, @Param("password") String password,
            @Param("name") String name,
            @Param("surname1") String surname1, @Param("surname2") String surname2,
            @Param("phoneNumber") Integer phoneNumber,
            @Param("birthdate") Date birthdate, @Param("sex") String sex,
            @Param("country") String country,
            @Param("town") String town, @Param("ethnic") String ethnic,
            @Param("bloodType") String bloodType,
            @Param("eyeColor") String eyeColor,
            @Param("biography") String biography);

    @Transactional
    @Query(value = "select * from Refugee where (name = :name or :name = '')"
            + "and (surname1 = :surname1 or :surname1 = '') and (surname2 = :surname2 or :surname2 = '')"
            + "and (birthdate = :birthdate or :birthdate = '1890-01-01') and (sex = :sex or :sex = '')"
            + "and (town = :town or :town = '') and (country = :country or :country = '')"
            + "and (bloodType = :bloodType or :bloodType = '') and (ethnic = :ethnic or :ethnic = '')"
            + "and (eyeColor = :eyeColor or :eyeColor = '')", nativeQuery = true)
    public Set<Refugee> findRefugeeByParams(@Param("name") String name,
            @Param("surname1") String surname1, @Param("surname2") String surname2,
            @Param("birthdate") String birthdate, @Param("sex") String sex,
            @Param("country") String country,
            @Param("town") String town, @Param("ethnic") String ethnic,
            @Param("bloodType") String bloodType,
            @Param("eyeColor") String eyeColor);

    public Optional<Refugee> findOptionalByMailAndPassword(String mail, String password);

    public void flush();

    public boolean existsByName(String name);

    public boolean existsBySurname1(String surname1);

    public Set<Refugee> findBySurname1(String surname1);

    public boolean existsBySurname2(String surname2);

    public Set<Refugee> findBySurname2(String surname2);

    public boolean existsByBirthdate(Date birthdate);

    public Set<Refugee> findByBirthdate(Date birthdate);

    public boolean existsBySex(String sex);

    public Set<Refugee> findBySex(String sex);

    public boolean existsByCountry(String country);

    public Set<Refugee> findByCountry(String country);

    public boolean existsByTown(String town);

    public Set<Refugee> findByTown(String town);

    public boolean existsByEthnic(String ethnic);

    public Set<Refugee> findByEthnic(String ethnic);

    public boolean existsByBloodType(String blood);

    public Set<Refugee> findByBloodType(String blood);

    public boolean existsByEyeColor(String eye);

    public Set<Refugee> findByEyeColor(String eye);

    public Boolean existsByMailAndLodges_Id(String mail, Long id);

    public Boolean existsByMailAndCourses_Id(String mail, Long id);

    public Boolean existsByMailAndDonations_Id(String mail, Long id);

    public Boolean existsByMailAndJobs_Id(String mail, Long id);
}
