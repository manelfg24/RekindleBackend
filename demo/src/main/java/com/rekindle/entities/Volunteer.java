package com.rekindle.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Volunteer extends User {
	
	
	public Volunteer(String mail, @NotNull String password, @NotNull String name, @NotNull String surname1,
			String surname2) {
		super(mail, password, name, surname1, surname2);
		
	}

}
