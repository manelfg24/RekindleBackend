package com.pes.rekindle.entities;

import java.io.Serializable;


public class ServiceEnrollmentKey implements Serializable{
	private String refugeeMail;
    private Long serviceId;
    
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
}
