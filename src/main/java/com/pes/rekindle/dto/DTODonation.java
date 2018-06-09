
package com.pes.rekindle.dto;

import java.sql.Time;

import com.pes.rekindle.entities.Donation;

public class DTODonation {
    private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private Integer places;
    private Time startTime;
    private Time endTime;
    private String description;

    public DTODonation() {
        super();
    }

    public DTODonation(Donation donation) {
        super();
        this.id = donation.getId();
        this.serviceType = donation.getServiceType();
        this.name = donation.getName();
        this.volunteer = donation.getVolunteer();
        this.phoneNumber = donation.getPhoneNumber();
        this.adress = donation.getAdress();
        this.places = donation.getPlaces();
        this.startTime = donation.getStartTime();
        this.endTime = donation.getEndTime();
        this.description = donation.getDescription();
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
