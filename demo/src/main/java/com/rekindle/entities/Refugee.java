package com.rekindle.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Refugee extends User {
	
	private int phoneNumber;
	private Date birthDate;
	private String sex;
	private String country;
	private String town;
	private String ethnic;
	private String bloodType;
	private String eyeColor;
	
	public Refugee(String mail, @NotNull String password, @NotNull String name, @NotNull String surname1,
			String surname2, int phoneNumber, Date birthDate, String sex, String country, String town, String ethnic,
			String bloodType, String eyeColor) {
		super(mail, password, name, surname1, surname2);
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.sex = sex;
		this.country = country;
		this.town = town;
		this.ethnic = ethnic;
		this.bloodType = bloodType;
		this.eyeColor = eyeColor;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getEthnic() {
		return ethnic;
	}
	
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	
	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	public String getEyeColor() {
		return eyeColor;
	}
	
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	
	
}
