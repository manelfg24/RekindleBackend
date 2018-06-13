package com.pes.rekindle.entities;

import java.io.Serializable;


public class JobEnrollmentKey implements Serializable{
	private String refugeeMail;
    private Long jobId;
    
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

    
}
