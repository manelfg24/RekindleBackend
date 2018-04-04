package com.rekindle.repositories;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.rekindle.entities.User;

@Transactional
public interface UserRepository extends CrudRepository<User, String> {
}
