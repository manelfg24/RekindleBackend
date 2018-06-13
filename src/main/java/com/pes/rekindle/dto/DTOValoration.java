
package com.pes.rekindle.dto;

import com.pes.rekindle.entities.DonationEnrollment;

public class DTOValoration {
	private String refugeeMail;
	private long idService;
	private String serviceType;
	private float points;
	
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public long getIdService() {
		return idService;
	}
	public void setIdService(long idService) {
		this.idService = idService;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
	
}
