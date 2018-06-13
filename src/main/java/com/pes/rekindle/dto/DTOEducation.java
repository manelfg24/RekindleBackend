
package com.pes.rekindle.dto;

import com.pes.rekindle.entities.Education;

public class DTOEducation {
	private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private String ambit;
    private String requirements;
    private String schedule;
    private Integer places;
    private Integer price;
    private String description;
    private Boolean ended;

    public DTOEducation() {
        super();
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DTOEducation(Education education) {
        super();
        this.id = education.getId();
        this.serviceType = education.getServiceType();
        this.name = education.getName();
        this.volunteer = education.getVolunteer();
        this.phoneNumber = education.getPhoneNumber();
        this.adress = education.getAdress();
        this.ambit = education.getAmbit();
        this.requirements = education.getRequirements();
        this.schedule = education.getSchedule();
        this.places = education.getPlaces();
        this.price = education.getPrice();
        this.description = education.getDescription();
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

    public String getAmbit() {
        return ambit;
    }

    public void setAmbit(String ambit) {
        this.ambit = ambit;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
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

	public Boolean getEnded() {
		return ended;
	}

	public void setEnded(Boolean ended) {
		this.ended = ended;
	}
}
