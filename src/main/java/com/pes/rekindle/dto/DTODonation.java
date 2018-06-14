
package com.pes.rekindle.dto;

//import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pes.rekindle.entities.Donation;

@JsonInclude(Include.NON_NULL)
public class DTODonation {
    private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private Integer places;
    private String startTime;
    private String endTime;
    private String description;
    private Boolean ended;

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
        
       // LocalTime endTime = LocalTime.parse(dtoDonation.getEndTime(), DateTimeFormatter.ofPattern("H:mm:ss"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        this.startTime = formatter.format(donation.getStartTime());
        System.out.println("StartTime:  --------------------------------");
        System.out.println(this.startTime);
        DateTimeFormatter formatter1 = DateTimeFormatter.ISO_LOCAL_TIME;
        System.out.println("EndTime:  --------------------------------");
        System.out.println(formatter1.format(donation.getEndTime()));
        this.endTime = formatter1.format(donation.getEndTime());
        this.description = donation.getDescription();
        this.ended = donation.getEnded();
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
    	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        this.startTime = formatter.format(startTime);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
    	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
        this.endTime = formatter.format(endTime);
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
