
package com.pes.rekindle.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Chat")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String informerUser;
    @NotNull
    private String reportedUser;
    @NotNull
    private String motive;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInformerUser() {
		return informerUser;
	}
	public void setInformerUser(String informerUser) {
		this.informerUser = informerUser;
	}
	public String getReportedUser() {
		return reportedUser;
	}
	public void setReportedUser(String reportedUser) {
		this.reportedUser = reportedUser;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
}
