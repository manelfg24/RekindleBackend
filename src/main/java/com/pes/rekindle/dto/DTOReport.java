
package com.pes.rekindle.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.pes.rekindle.entities.Refugee;

public class DTOReport {

    private long idReport;
    
    private DTOUser informerUser;
    
    private DTOUser reportedUser;
    
    private String motive;

	public long getIdReport() {
		return idReport;
	}

	public void setIdReport(long idReport) {
		this.idReport = idReport;
	}

	public DTOUser getInformerUser() {
		return informerUser;
	}

	public void setInformerUser(DTOUser informerUser) {
		this.informerUser = informerUser;
	}

	public DTOUser getReportedUser() {
		return reportedUser;
	}

	public void setReportedUser(DTOUser reportedUser) {
		this.reportedUser = reportedUser;
	}

	public String getMotive() {
		return motive;
	}

	public void setMotive(String motive) {
		this.motive = motive;
	}   
}
