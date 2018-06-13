
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.dto.DTODonationEnrollment;

@Entity
@IdClass(EducationEnrollmentKey.class)
@Table(name = "EducationEnrollment")
public class EducationEnrollment {

	@Id
	private String refugeeMail;
    @Id
    private Long educationId;
    @NotNull
    private float valoration;
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
	public float getValoration() {
		return valoration;
	}
	public void setValoration(float valoration) {
		this.valoration = valoration;
	}
}
