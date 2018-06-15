package com.pes.rekindle.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class EducationEnrollmentKey implements Serializable{
	private String refugeeMail;
    private Long educationId;
    
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((educationId == null) ? 0 : educationId.hashCode());
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
		EducationEnrollmentKey other = (EducationEnrollmentKey) obj;
		if (educationId == null) {
			if (other.educationId != null)
				return false;
		} else if (!educationId.equals(other.educationId))
			return false;
		if (refugeeMail == null) {
			if (other.refugeeMail != null)
				return false;
		} else if (!refugeeMail.equals(other.refugeeMail))
			return false;
		return true;
	}
}
