package com.pes.rekindle.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Lodge")
public class Lodge {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
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
    private Date dateLimit;
    @NotNull
    private String description;

    
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
	public Date getDateLimit() {
		return dateLimit;
	}
	public void setDateLimit(Date dateLimit) {
		this.dateLimit = dateLimit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
	
}


