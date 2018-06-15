
package com.pes.rekindle.dto;

import java.util.Date;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;

public class DTOService {
	private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private String description;
    private double positionLat;
    private double positionLng;

	public DTOService() {
        super();
    }

    public DTOService(Lodge lodge) {
        super();
        this.id = lodge.getId();
        this.serviceType = lodge.getServiceType();
        this.name = lodge.getName();
        this.volunteer = lodge.getVolunteer();
        this.phoneNumber = lodge.getPhoneNumber();
        this.adress = lodge.getAdress();
        this.description = lodge.getDescription();
        this.positionLat = lodge.getPositionLat();
        this.positionLng = lodge.getPositionLng();
    }
    
    public DTOService(Donation donation) {
        super();
        this.id = donation.getId();
        this.serviceType = donation.getServiceType();
        this.name = donation.getName();
        this.volunteer = donation.getVolunteer();
        this.phoneNumber = donation.getPhoneNumber();
        this.adress = donation.getAdress();
        this.description = donation.getDescription();
        this.positionLat = donation.getPositionLat();
        this.positionLng = donation.getPositionLng();
    }
    
    public DTOService(Education education) {
        super();
        this.id = education.getId();
        this.serviceType = education.getServiceType();
        this.name = education.getName();
        this.volunteer = education.getVolunteer();
        this.phoneNumber = education.getPhoneNumber();
        this.adress = education.getAdress();
        this.description = education.getDescription();
        this.positionLat = education.getPositionLat();
        this.positionLng = education.getPositionLng();
    }
  
    public DTOService(Job job) {
        super();
        this.id = job.getId();
        this.serviceType = job.getServiceType();
        this.name = job.getName();
        this.volunteer = job.getVolunteer();
        this.phoneNumber = job.getPhoneNumber();
        this.adress = job.getAdress();
        this.description = job.getDescription();
        this.positionLat = job.getPositionLat();
        this.positionLng = job.getPositionLng();
    }
    
    public DTOService(DTOFilterService dtoFilterService) {
        this.id = dtoFilterService.getId();
        this.serviceType = dtoFilterService.getServiceType();
        this.name = dtoFilterService.getName();
        this.volunteer = dtoFilterService.getVolunteer();
        this.phoneNumber = dtoFilterService.getPhoneNumber();
        this.adress = dtoFilterService.getAdress();
        this.description = dtoFilterService.getDescription();
        this.positionLat = dtoFilterService.getPositionLat();
        this.positionLng = dtoFilterService.getPositionLng();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public double getPositionLat() {
		return positionLat;
	}

	public void setPositionLat(double positionLat) {
		this.positionLat = positionLat;
	}

	public double getPositionLng() {
		return positionLng;
	}

	public void setPositionLng(double positionLng) {
		this.positionLng = positionLng;
	}
}
