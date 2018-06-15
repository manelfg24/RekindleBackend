
package com.pes.rekindle.dao;

public class DAOService {
    private long id;
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private String description;
    private double positionLat;
    private double positionLng;
    private double distance;
    
    public DAOService() {
    	super();
	}

    public DAOService(Object[] dao) {
    	super();
    	this.id = Long.parseLong(dao[0].toString());
    	this.serviceType = (String) dao[1];
    	this.name = (String) dao[2];
    	this.volunteer = (String) dao[3];
    	this.phoneNumber = (Integer) dao[4];
    	this.adress = (String) dao[5];
    	this.description = (String) dao[6];
    	this.positionLat = (double) dao[7];
    	this.positionLng = (double) dao[8];
    	this.distance = (double) dao[9];
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
