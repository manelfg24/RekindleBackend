
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.dto.DTODonationEnrollment;

@Entity
@IdClass(ServiceEnrollmentKey.class)
@Table(name = "JobEnrollment")
public class JobEnrollment {

	@Id
	private String refugeeMail;
    @Id
    private Long jobId;
    private float valoration;
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
	public float getValoration() {
		return valoration;
	}
	public void setValoration(float valoration) {
		this.valoration = valoration;
	}
}
