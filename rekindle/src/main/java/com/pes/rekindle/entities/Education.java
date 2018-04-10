package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    @NotNull
    private String name;
    @NotNull
    private String volunteer;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    private String adress;
    @NotNull
    private String ambit;
    @NotNull
    private String prerequisite;
    private String schedule;
    private Integer places;
    private Integer price;
    @NotNull
    private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVolunteer() {
		return volunteer;
	}
	public void setVolunteer(String volunteer) {
		this.volunteer = volunteer;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getAmbit() {
		return ambit;
	}
	public void setAmbit(String ambit) {
		this.ambit = ambit;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public Integer getPlaces() {
		return places;
	}
	public void setPlaces(Integer places) {
		this.places = places;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
   
}


