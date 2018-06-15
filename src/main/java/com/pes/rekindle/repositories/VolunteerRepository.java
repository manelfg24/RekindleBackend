
package com.pes.rekindle.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pes.rekindle.entities.Volunteer;

@Transactional
public interface VolunteerRepository extends Repository<Volunteer, String> {
    public Volunteer findByMail(String mail);

    public Optional<Volunteer> findOptionalByMail(String mail);

    public Volunteer findByMailAndPassword(String mail, String password);

    public Optional<Volunteer> findOptionalByMailAndPassword(String mail, String password);

    public void save(Volunteer volunteer);

    @Modifying
    @Transactional
    @Query(value = "insert into Volunteer"
            + " values(:mail, :password, :name, :surname1, :surname2)", nativeQuery = true)
    public void create(@Param("mail") String mail, @Param("password") String password,
            @Param("name") String name,
            @Param("surname1") String surname1, @Param("surname2") String surname2);

    public void flush();

	public Volunteer findByMail(Volunteer volunteer);

	public Set<Volunteer> findAll();
}
