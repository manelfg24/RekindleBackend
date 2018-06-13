
package com.pes.rekindle.dto;

import com.pes.rekindle.entities.DonationEnrollment;

public class DTODonationEnrollment {
	private String refugeeMail;
	private DTOService donation;
	private String motive;
	
	public DTODonationEnrollment() {
		super();
	}
	
	public DTODonationEnrollment(DonationEnrollment donationEnrollment, DTOService dtoDonation) {
		super();
		this.refugeeMail = donationEnrollment.getRefugeeMail();
		this.donation = dtoDonation;
		this.motive = donationEnrollment.getMotive();
	}
	
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public DTOService getDonation() {
		return donation;
	}
	public void setDonation(DTOService donation) {
		this.donation = donation;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
}
