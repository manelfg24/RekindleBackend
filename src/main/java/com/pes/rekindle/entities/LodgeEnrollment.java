
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.dto.DTODonationEnrollment;

@Entity
@IdClass(LodgeEnrollmentKey.class)
@Table(name = "LodgeEnrollment")
public class LodgeEnrollment {

	@Id
	private String refugeeMail;
    @Id
    private Long lodgeId;
    private float valoration;
    
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
	public float getValoration() {
		return valoration;
	}
	public void setValoration(float valoration) {
		this.valoration = valoration;
	}
}
