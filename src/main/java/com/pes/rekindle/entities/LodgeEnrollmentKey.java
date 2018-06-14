package com.pes.rekindle.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class LodgeEnrollmentKey implements Serializable{
	private String refugeeMail;
    private Long lodgeId;
    
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getLodgeId() {
		return lodgeId;
	}
	public void setLodgeId(Long lodgeId) {
		this.lodgeId = lodgeId;
	}
   
}
