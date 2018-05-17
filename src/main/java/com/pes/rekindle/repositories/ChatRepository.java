
package com.pes.rekindle.repositories;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.Chat;

@Transactional
public interface ChatRepository extends Repository<Chat, String> {
    public boolean existsByMailUser1(String mail);

    public boolean existsByMailUser2(String mail);

    public Set<Chat> findByMailUser1(String mail);

    public Set<Chat> findByMailUser2(String mail);

    public void save(Chat chat);

    public Chat findByMailUser1AndMailUser2(String mail, String mail2);
}
