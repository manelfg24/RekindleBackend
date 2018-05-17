
package com.pes.rekindle.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Volunteer;

@Transactional
public interface ChatRepository extends Repository<Chat, String> {
	public boolean existsByMailUser1(String mail);
	public boolean existsByMailUser2(String mail);
	
	public Set<Chat> findByMailUser1(String mail);
	public Set<Chat> findByMailUser2(String mail);
	
	public Chat findByMailUser1AndMailUser2(String mailUser1, String mailUser2);
	public void save(Chat chat);
}
