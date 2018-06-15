
package com.pes.rekindle.dto;

import com.pes.rekindle.entities.DonationEnrollment;

public class DTODonationEnrollment {
    private String refugeeMail;
    private DTOService donation;
    private String motive;
    private float valoration;

    public DTODonationEnrollment() {
        super();
    }

    public DTODonationEnrollment(DonationEnrollment donationEnrollment, DTOService dtoDonation) {
        super();
        this.refugeeMail = donationEnrollment.getRefugeeMail();
        this.donation = dtoDonation;
        this.motive = donationEnrollment.getMotive();
        this.valoration = donationEnrollment.getValoration();
    }

    public String getRefugeeMail() {
        return refugeeMail;
    }

    public void setRefugeeMail(String refugeeMail) {
        this.refugeeMail = refugeeMail;
    }

    public DTOService getDonation() {
        return donation;
    }

    public void setDonation(DTOService donation) {
        this.donation = donation;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public float getValoration() {
        return valoration;
    }

    public void setValoration(float valoration) {
        this.valoration = valoration;
    }
}
