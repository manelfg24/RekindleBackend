
package com.pes.rekindle.dto;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.entities.Refugee;

public class DTOChat {

    private long id;
    
    private DTOUser user1;
    
    private DTOUser user2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DTOUser getUser1() {
		return user1;
	}

	public void setUser1(DTOUser user1) {
		this.user1 = user1;
	}

	public DTOUser getUser2() {
		return user2;
	}

	public void setUser2(DTOUser user2) {
		this.user2 = user2;
	}
}
