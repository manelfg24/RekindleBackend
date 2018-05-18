
package com.pes.rekindle.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Message;

@Transactional
public interface MessageRepository extends Repository<Message, String> {

	
}
