package com.pes.rekindle.entities;

import java.io.Serializable;


public class DonationEnrollmentKey implements Serializable{
	private String refugeeMail;
    private Long donationId;
    
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getDonationId() {
		return donationId;
	}
	public void setDonationId(Long donationId) {
		this.donationId = donationId;
	}
}
