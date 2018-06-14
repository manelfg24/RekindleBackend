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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lodgeId == null) ? 0 : lodgeId.hashCode());
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
		LodgeEnrollmentKey other = (LodgeEnrollmentKey) obj;
		if (lodgeId == null) {
			if (other.lodgeId != null)
				return false;
		} else if (!lodgeId.equals(other.lodgeId))
			return false;
		if (refugeeMail == null) {
			if (other.refugeeMail != null)
				return false;
		} else if (!refugeeMail.equals(other.refugeeMail))
			return false;
		return true;
	}
   
}
