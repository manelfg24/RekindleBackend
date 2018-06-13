
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Volunteer")
public class Volunteer {

    @Id
    private String mail;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String surname1;
    private String surname2;
    private String photo;
    private float averageValoration;
    private int numberOfValorations;
    private String apiKey;

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

    public int getNumberOfValorations() {
        return numberOfValorations;
    }

    public void setNumberOfValorations(int numberOfValorations) {
        this.numberOfValorations = numberOfValorations;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
