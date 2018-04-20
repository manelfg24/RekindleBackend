
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private char serviceType;
    @NotNull
    private String name;
    @NotNull
    private String volunteer;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    private String adress;
    @NotNull
    private String charge;
    @NotNull
    private String requirements;
    @NotNull
    private double hoursDay;
    @NotNull
    private double hoursWeek;
    @NotNull
    private Integer contractDuration;
    private Integer places;
    @NotNull
    private double salary;
    @NotNull
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getServiceType() {
        return serviceType;
    }

    public void setServiceType(char serviceType) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
