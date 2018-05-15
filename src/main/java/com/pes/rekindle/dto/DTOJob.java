
package com.pes.rekindle.dto;

import com.pes.rekindle.entities.Job;

public class DTOJob {
    private String serviceType;
    private String name;
    private String volunteer;
    private Integer phoneNumber;
    private String adress;
    private String charge;
    private String requirements;
    private double hoursDay;
    private double hoursWeek;
    private Integer contractDuration;
    private Integer places;
    private double salary;
    private String description;

    public DTOJob() {
        super();
    }

    public DTOJob(Job job) {
        super();
        this.serviceType = job.getServiceType();
        this.name = job.getName();
        this.volunteer = job.getVolunteer();
        this.phoneNumber = job.getPhoneNumber();
        this.adress = job.getAdress();
        this.charge = job.getCharge();
        this.requirements = job.getRequirements();
        this.hoursDay = job.getHoursDay();
        this.hoursWeek = job.getHoursWeek();
        this.contractDuration = job.getContractDuration();
        this.places = job.getPlaces();
        this.salary = job.getSalary();
        this.description = job.getDescription();
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

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public double getHoursDay() {
        return hoursDay;
    }

    public void setHoursDay(double hoursDay) {
        this.hoursDay = hoursDay;
    }

    public double getHoursWeek() {
        return hoursWeek;
    }

    public void setHoursWeek(double hoursWeek) {
        this.hoursWeek = hoursWeek;
    }

    public Integer getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(Integer contractDuration) {
        this.contractDuration = contractDuration;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
