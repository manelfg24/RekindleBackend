
package com.pes.rekindle.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.pes.rekindle.entities.Admin;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;

@JsonInclude(Include.NON_NULL)
public class DTOUser {
    private String mail;
    private String password;
    private String userType;
    private String name;
    private String surname1;
    private String surname2;
    private Integer phoneNumber;
    private String birthdate;
    private String sex;
    private String country;
    private String town;
    private String ethnic;
    private String bloodType;
    private String eyeColor;
    private String biography;
    private String photo;
    private float averageValoration;
    private int enabled;
    private String apiKey;


    public DTOUser() {
        super();
    }

    public DTOUser(Refugee refugee) {
        super();
        this.mail = refugee.getMail();
        this.password = refugee.getPassword();
        this.userType = "Refugee";
        this.name = refugee.getName();
        this.surname1 = refugee.getSurname1();
        this.surname2 = refugee.getSurname2();
        this.phoneNumber = refugee.getPhoneNumber();
        if (refugee.getBirthdate() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.birthdate = formatter.format(refugee.getBirthdate());
        }
        this.sex = refugee.getSex();
        this.country = refugee.getCountry();
        this.town = refugee.getTown();
        this.ethnic = refugee.getEthnic();
        this.bloodType = refugee.getBloodType();
        this.eyeColor = refugee.getEyeColor();
        this.biography = refugee.getBiography();
        this.photo = refugee.getPhoto();
        this.enabled = refugee.getEnabled();
    }

    public DTOUser(Volunteer volunteer) {
        super();
        this.mail = volunteer.getMail();
        this.password = volunteer.getPassword();
        this.userType = "Volunteer";
        this.name = volunteer.getName();
        this.surname1 = volunteer.getSurname1();
        this.surname2 = volunteer.getSurname2();
        this.photo = volunteer.getPhoto();
        this.enabled = volunteer.getEnabled();
        if (volunteer.getNumberOfValorations() == 0) {
            this.averageValoration = 0;
        } else {
            this.averageValoration = volunteer.getAverageValoration()
                    / volunteer.getNumberOfValorations();
        }

    }

    public DTOUser(Admin admin) {
        super();
        this.mail = admin.getMail();
        this.password = admin.getPassword();
        this.userType = "Admin";
        this.name = admin.getName();
        this.surname1 = admin.getSurname1();
        this.surname2 = admin.getSurname2();
        this.photo = admin.getPhoto();
        this.enabled = admin.getEnabled();
    }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    public float getAverageValoration() {
        return averageValoration;
    }

    public void setAverageValoration(float averageValoration) {
        this.averageValoration = averageValoration;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public int getEnabled() {
      return enabled;
    }

    public void setEnabled(int enabled) {
      this.enabled = enabled;
    }

}
