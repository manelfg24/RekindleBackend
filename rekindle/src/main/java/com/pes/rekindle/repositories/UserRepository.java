package com.pes.rekindle.repositories;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.pes.rekindle.entities.User;

@Transactional
public interface UserRepository extends CrudRepository<User, String> {
}
