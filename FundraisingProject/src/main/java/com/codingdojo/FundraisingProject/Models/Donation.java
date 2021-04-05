package com.codingdojo.FundraisingProject.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="donations")
public class Donation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long amount;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private long date;
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor donor;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User donation_uploader;
    
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
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
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
    
}
