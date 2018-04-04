package com.rekindle.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Education extends Service {
	
	private String requirements;
	private String schedule;
	private String theme;
	private int places;
	private double price;
	
	public Education(int id, @NotNull String name, @NotNull String phone, @NotNull String address, String description,
			@NotNull String volunteer, String requirements, String schedule, String theme, int places, double price) {
		super(id, name, phone, address, description, volunteer);
		this.requirements = requirements;
		this.schedule = schedule;
		this.theme = theme;
		this.places = places;
		this.price = price;
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
