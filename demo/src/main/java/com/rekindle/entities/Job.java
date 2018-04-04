package com.rekindle.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Job extends Service {
	
	@NotNull
	private String charge;
	private String requirements;
	private double hoursDay;
	private double hoursWeek;
	private int contractDuration;
	private double salary;
	private int places;
	
	public Job(int id, @NotNull String name, @NotNull String phone, @NotNull String address, String description,
			@NotNull String volunteer, @NotNull String charge, String requirements, double hoursDay, double hoursWeek,
			int contractDuration, double salary, int places) {
		super(id, name, phone, address, description, volunteer);
		this.charge = charge;
		this.requirements = requirements;
		this.hoursDay = hoursDay;
		this.hoursWeek = hoursWeek;
		this.contractDuration = contractDuration;
		this.salary = salary;
		this.places = places;
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

	public int getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(int contractDuration) {
		this.contractDuration = contractDuration;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}
	
	
	
}
