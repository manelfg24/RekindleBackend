package com.rekindle.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Lodge extends Service {
	
	private int places;
	private Date deadline;
	
	public Lodge(int id, @NotNull String name, @NotNull String phone, @NotNull String address, String description,
			@NotNull String volunteer, int places, Date deadline) {
		super(id, name, phone, address, description, volunteer);
		this.places = places;
		this.deadline = deadline;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
}
