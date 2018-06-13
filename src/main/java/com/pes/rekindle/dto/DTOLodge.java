
package com.pes.rekindle.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pes.rekindle.entities.Lodge;

public class DTOLodge {
    private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private Integer places;
    private String dateLimit;
    private String description;
    private Boolean ended;

    public DTOLodge() {
        super();
    }

    public DTOLodge(Lodge lodge) {
        super();
        this.id = lodge.getId();
        this.serviceType = lodge.getServiceType();
        this.name = lodge.getName();
        this.volunteer = lodge.getVolunteer();
        this.phoneNumber = lodge.getPhoneNumber();
        this.adress = lodge.getAdress();
        this.places = lodge.getPlaces();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dateLimit = formatter.format(lodge.getDateLimit());
        this.description = lodge.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public String getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dateLimit = formatter.format(dateLimit);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Boolean getEnded() {
		return ended;
	}

	public void setEnded(Boolean ended) {
		this.ended = ended;
	}
}
