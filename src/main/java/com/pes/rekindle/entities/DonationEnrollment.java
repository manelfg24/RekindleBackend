
package com.pes.rekindle.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.dto.DTODonationEnrollment;

@Entity
@IdClass(DonationEnrollmentKey.class)
@Table(name = "DonationEnrollment")
public class DonationEnrollment {

	@Id
	private String refugeeMail;
    @Id
    private Long donationId;
    @NotNull
    private String motive;
    private String requestStatus;
    private float valoration;
    
    public DonationEnrollment() {
    	super();
    }
    
	public DonationEnrollment(DTODonationEnrollment dtoDonationEnrollment) {
		super();
		this.refugeeMail = dtoDonationEnrollment.getRefugeeMail();
		this.donationId = dtoDonationEnrollment.getDonation().getId();
		this.motive = dtoDonationEnrollment.getMotive();
		//Hardcodeado a falso porque solo lo usaremos cuando se crea un servicio
		this.requestStatus = "Not Resolved";
		this.valoration = 0;
	}
	public String getRefugeeMail() {
		return refugeeMail;
	}
	public void setRefugeeMail(String refugeeMail) {
		this.refugeeMail = refugeeMail;
	}
	public Long getDonationId() {
		return donationId;
	}
	public void setDonationId(Long donationId) {
		this.donationId = donationId;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public float getValoration() {
		return valoration;
	}

	public void setValoration(float valoration) {
		this.valoration = valoration;
	}
	
}
