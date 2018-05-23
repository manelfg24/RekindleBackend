
package com.pes.rekindle.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pes.rekindle.dto.DTOUser;

@Entity
@Table(name = "Refugee")
public class Refugee {

    @Id
    private String mail;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname1;
    private String surname2;
    private Integer phoneNumber;
    private Date birthdate;
    private String sex;
    private String country;
    private String town;
    private String ethnic;
    private String bloodType;
    private String eyeColor;
    private String biography;
    private String photo; 

    
    @ManyToMany(mappedBy="inscriptions",
    		cascade = {
    		CascadeType.PERSIST, 
    		CascadeType.MERGE
    })
    private Set<Lodge> lodges = new HashSet<Lodge>();

    
    @ManyToMany(mappedBy="inscriptions",
    		cascade = {
    		CascadeType.PERSIST, 
    		CascadeType.MERGE,
    })
    @JsonBackReference
    private Set<Donation> donations = new HashSet<Donation>();

    
    @ManyToMany(mappedBy="inscriptions",
    		cascade = {
    		CascadeType.PERSIST, 
    		CascadeType.MERGE
    })
    @JsonBackReference
    private Set<Job> jobs = new HashSet<Job>();

    
    @ManyToMany(mappedBy="inscriptions",
    		cascade = {
    		CascadeType.PERSIST, 
    		CascadeType.MERGE
    })
    @JsonBackReference
    private Set<Education> courses = new HashSet<Education>();
    
    public Refugee() {
        super();
    }

    public Refugee(DTOUser refugee) {
        super();
        this.mail = refugee.getMail();
        this.password = refugee.getPassword();
        this.name = refugee.getName();
        this.surname1 = refugee.getSurname1();
        this.surname2 = refugee.getSurname2();
        this.phoneNumber = refugee.getPhoneNumber();
        this.birthdate = refugee.getBirthdate();
        this.sex = refugee.getSex();
        this.country = refugee.getCountry();
        this.town = refugee.getTown();
        this.ethnic = refugee.getEthnic();
        this.bloodType = refugee.getBloodType();
        this.eyeColor = refugee.getEyeColor();
        this.biography = refugee.getBiography();
        this.photo = refugee.getPhoto();
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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

	public Set<Lodge> getLodges() {
		return lodges;
	}

	public void setLodges(Set<Lodge> lodges) {
		this.lodges = lodges;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<Education> getCourses() {
		return courses;
	}

	public void setCourses(Set<Education> courses) {
		this.courses = courses;
	}
}
