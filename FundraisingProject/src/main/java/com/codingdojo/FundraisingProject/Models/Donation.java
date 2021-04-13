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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="donations")
public class Donation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Double amount;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date Dondate;
	@DateTimeFormat(pattern ="kk:mm")
	private Date Dontime;
	/*@NotNull
	private String refcode;*/
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User donation_uploader;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="email_id")
    private Emails emailDonation;
    
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
    public Donation() {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Date getDondate() {
		return Dondate;
	}

	public void setDondate(Date dondate) {
		Dondate = dondate;
	}
	

	public Date getDontime() {
		return Dontime;
	}

	public void setDontime(Date dontime) {
		Dontime = dontime;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public User getDonation_uploader() {
		return donation_uploader;
	}

	public void setDonation_uploader(User donation_uploader) {
		this.donation_uploader = donation_uploader;
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
    public String getDonationDateFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	return df.format(this.Dondate);
    }
    public String getDonationTimeFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("kk:mm");
    	return df.format(this.Dontime);
    }
    
	/*public Donation mostRecentDonation(Donor donor) {
		List<Donation> contributions = donor.getContributions();
		Donation mostRecent = contributions.get(0);
		//for (int i = 0; i < contributions.size(); i++) {
			//if (contributions.get(i).getId() > mostRecent.getId()) {
				//mostRecent = contributions.get(i);
			//}
		//}
		return mostRecent;
	}*/

	/*public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}*/

	public Emails getEmailDonation() {
		return emailDonation;
	}

	public void setEmailDonation(Emails emailDonation) {
		this.emailDonation = emailDonation;
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
