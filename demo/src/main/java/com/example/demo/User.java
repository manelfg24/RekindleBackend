package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="User")

public class User {
	
	@Id
	@Column(name="mail")
    private String mail;
    
    @Column(name="password", nullable=false)
    private String password;
    
    @Column(name="name", nullable=false)
    private String name;
    
    @Column(name="surname1", nullable=false)
    private String surname1;
    
    @Column(name="surname2")
    private String surname2;
    
    public User() {
    	super();
    }
    
    public User(String mail, String password, String name, String surname1, String surname2) {
		super();
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

