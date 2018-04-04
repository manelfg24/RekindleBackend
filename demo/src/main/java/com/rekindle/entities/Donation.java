package com.rekindle.entities;

import java.sql.Time;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Donation extends Service {
	
	private int maxRequests;
	private Time startHour;
	private Time finishHour;
	
	public Donation(int id, @NotNull String name, @NotNull String phone, @NotNull String address, String description,
			@NotNull String volunteer, int maxRequests, Time startHour, Time finishHour) {
		super(id, name, phone, address, description, volunteer);
		this.maxRequests = maxRequests;
		this.startHour = startHour;
		this.finishHour = finishHour;
	}

	public int getMaxRequests() {
		return maxRequests;
	}

	public void setMaxRequests(int maxRequests) {
		this.maxRequests = maxRequests;
	}

	public Time getStartHour() {
		return startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}

	public Time getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(Time finishHour) {
		this.finishHour = finishHour;
	}
	
	
	
}
