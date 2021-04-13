package com.codingdojo.FundraisingProject.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="donors")
public class Donor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String donorFirstName;
	private String donorLastName;
	@NotEmpty
	@Email(message="Must be a valid email.")
	private String donorEmail;
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="donor")
    private List<Donation> contributions;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User uploader;
    
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	public Donor() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDonorFirstName() {
		return donorFirstName;
	}

	public void setDonorFirstName(String donorFirstName) {
		this.donorFirstName = donorFirstName;
	}

	public String getDonorLastName() {
		return donorLastName;
	}

	public void setDonorLastName(String donorLastName) {
		this.donorLastName = donorLastName;
	}

	public List<Donation> getContributions() {
		return contributions;
	}

	public void setContributions(List<Donation> contributions) {
		this.contributions = contributions;
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
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
	
	public String getDonorEmail() {
		return donorEmail;
	}

	public void setDonorEmail(String email) {
		this.donorEmail = email;
	}

	/*public Double getMostRecentdonation() {
		contributions.get(0).getAmount();
		Donation mostRecent = contributions.get(0);
		for (int i = 0; i < contributions.size(); i++) {
		if (contributions.get(i).getDate() > mostRecent.getDate()) {
				mostRecent = contributions.get(i);
			}
		}
		return mostRecent.getAmount();
	}*/
	
	public Double getMostRecentdonation() {
	Donation mostRecent = new Donation();
	Double mostRecentAmount = 0.0;
	if (contributions.size() > 0) {
		for (int i = 0; i < contributions.size(); i++) {
			if (contributions.get(i).getId() > mostRecent.getId()) {
					mostRecentAmount = contributions.get(i).getAmount();
				}
			}
	}
	return mostRecentAmount;
   }
	
	public String getMostRecentdonationDate() {
	Donation mostRecent = new Donation();
	String mostRecentDate = null;
	if (contributions.size() > 0) {
		for (int i = 0; i < contributions.size(); i++) {
			if (contributions.get(i).getId() > mostRecent.getId()) {
					mostRecentDate = contributions.get(i).getDonationDateFormatted();
				}
			}
	}
	return mostRecentDate;
   }
	
	/*public Donation getMostRecentdonation() {
		Donation mostRecent = new Donation();
		if (contributions.size() > 0) {
			for (int i = 0; i < contributions.size(); i++) {
				if (contributions.get(i).getId() < mostRecent.getId()) {
						mostRecent = contributions.get(i);
					}
				}
		}
		return mostRecent;
	}
	
	/*public String getMostRecentdonationDATE() {
		String MostRecentdonationDATE = contributions.get(0).getDonationDateFormatted();
		Donation mostRecent = contributions.get(0);
		for (int i = 0; i < contributions.size(); i++) {
		if (contributions.get(i).getId() > mostRecent.getId()) {
				mostRecent = contributions.get(i);
			}
		}
		MostRecentdonationDATE = mostRecent.getDonationDateFormatted();
		return MostRecentdonationDATE;
	}

	/*public void setMostRecentdonation(Double mostRecentdonation) {
		this.mostRecentdonation = mostRecentdonation;
	}*/

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}

	/*public void setMostRecentdonationDATE(String mostRecentdonationDATE) {
		MostRecentdonationDATE = mostRecentdonationDATE;
	}*/
}
