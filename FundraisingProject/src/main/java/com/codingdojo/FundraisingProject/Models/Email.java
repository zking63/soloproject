package com.codingdojo.FundraisingProject.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="emails")
public class Email {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String emailName;
	@DateTimeFormat(pattern ="yyyy-MM-dd, kk:mm")
	private Date Emaildate;
	@NotNull
	private String refcode;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	/*@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="donors_emails",
		joinColumns = @JoinColumn(name="email_id"),
		inverseJoinColumns = @JoinColumn(name="donor_id")
	)
	private List<Donor> Emaildonors;*/
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="donations_emails",
		joinColumns = @JoinColumn(name="email_refcode"),
		inverseJoinColumns = @JoinColumn(name="donation_refcode")
	)
	private List<Donation> Emaildonations;
	
	public Email() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	public Date getEmaildate() {
		return Emaildate;
	}

	public void setEmaildate(Date emaildate) {
		Emaildate = emaildate;
	}

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Donation> getEmaildonations() {
		return Emaildonations;
	}

	public void setEmaildonations(List<Donation> emaildonations) {
		Emaildonations = emaildonations;
	}
    public String getDonationDateFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd, kk:mm");
    	return df.format(this.Emaildate);
    }
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
}
