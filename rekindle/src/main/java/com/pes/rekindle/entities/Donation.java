package com.pes.rekindle.entities;

import java.sql.Time;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Donation")
public class Donation {
	/*Tiene pinta de que falta una fecha o las horas son fechas*/
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
    private Integer places;
    @NotNull
    private Time startTime;
    @NotNull
    private Time endTime;
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
	public Integer getPlaces() {
		return places;
	}
	public void setPlaces(Integer places) {
		this.places = places;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

