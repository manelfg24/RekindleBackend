
package com.pes.rekindle.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pes.rekindle.entities.Admin;

@Transactional
public interface AdminRepository extends Repository<Admin, String> {
    public Admin findByMail(String mail);

    public Optional<Admin> findOptionalByMail(String mail);

    public Admin findByMailAndPassword(String mail, String password);

    public Optional<Admin> findOptionalByMailAndPassword(String mail, String password);

    public void save(Admin admin);

    @Modifying
    @Transactional
    @Query(value = "insert into admin"
            + " values(:mail, :password, :name, :surname1, :surname2)", nativeQuery = true)
    public void create(@Param("mail") String mail, @Param("password") String password,
            @Param("name") String name,
            @Param("surname1") String surname1, @Param("surname2") String surname2);

    public void flush();
}
