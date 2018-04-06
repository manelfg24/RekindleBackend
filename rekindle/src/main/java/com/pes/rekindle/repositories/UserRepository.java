package com.pes.rekindle.repositories;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.User;

@Transactional
public interface UserRepository extends Repository<User, String> {
	public User findByMail(String mail);
	public Optional<User> findOptionalByMail(String mail);
	public void save(User u);
}
