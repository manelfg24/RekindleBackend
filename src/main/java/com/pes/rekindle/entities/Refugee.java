
package com.pes.rekindle.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @NotNull
    private int enabled;
    private String apiKey;

    @ManyToMany(mappedBy = "inscriptions", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Lodge> lodges = new HashSet<Lodge>();

    @ManyToMany(mappedBy = "inscriptions", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    @JsonBackReference
    private Set<Donation> donations = new HashSet<Donation>();

    @ManyToMany(mappedBy = "inscriptions", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonBackReference
    private Set<Job> jobs = new HashSet<Job>();

    @ManyToMany(mappedBy = "inscriptions", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonBackReference
    private Set<Education> courses = new HashSet<Education>();

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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
