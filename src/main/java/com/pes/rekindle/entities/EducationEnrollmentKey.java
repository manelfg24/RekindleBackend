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
}
