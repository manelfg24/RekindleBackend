package com.pes.rekindle.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
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
		JobEnrollmentKey other = (JobEnrollmentKey) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (refugeeMail == null) {
			if (other.refugeeMail != null)
				return false;
		} else if (!refugeeMail.equals(other.refugeeMail))
			return false;
		return true;
	}

    
}
