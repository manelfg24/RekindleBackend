package com.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
    protected String mail;
    @NotNull
    protected String password;
    @NotNull
    protected String name;
    @NotNull
    protected String surname1;
    protected String surname2;
    
	public User(String mail, @NotNull String password, @NotNull String name, @NotNull String surname1,
			String surname2) {
		this.mail = mail;
		this.password = password;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname1() {
		return surname1;
	}
	
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	
	public String getSurname2() {
		return surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
    
}


