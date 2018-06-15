package com.pes.rekindle.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((donationId == null) ? 0 : donationId.hashCode());
		result = prime * result + ((refugeeMail == null) ? 0 : refugeeMail.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonationEnrollmentKey other = (DonationEnrollmentKey) obj;
		if (donationId == null) {
			if (other.donationId != null)
				return false;
		} else if (!donationId.equals(other.donationId))
			return false;
		if (refugeeMail == null) {
			if (other.refugeeMail != null)
				return false;
		} else if (!refugeeMail.equals(other.refugeeMail))
			return false;
		return true;
	}
}
