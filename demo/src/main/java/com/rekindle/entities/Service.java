package com.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Service {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO )
	private int id;
	
	@NotNull
	private String name;
	@NotNull
	private String phone;
	@NotNull
	private String address;
	private String description;
	
	@ManyToOne
	@NotNull
	private String volunteer;

	public Service(int id, @NotNull String name, @NotNull String phone, @NotNull String address, String description,
			@NotNull String volunteer) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.description = description;
		this.volunteer = volunteer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(String volunteer) {
		this.volunteer = volunteer;
	}
	
	
}
