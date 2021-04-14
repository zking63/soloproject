package com.codingdojo.FundraisingProject.Models;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="emails")
public class Emails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String emailName;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date Emaildate;
	@DateTimeFormat(pattern ="kk:mm")
	private Date Emailtime;
	@NotNull
	private String emailRefcode;
	private Double emailAverage;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User email_uploader;
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="emailDonation")
	private List<Donation> Emaildonations;
	
	public Emails() {
		
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
	
	

	public Date getEmailtime() {
		return Emailtime;
	}

	public void setEmailtime(Date emailtime) {
		Emailtime = emailtime;
	}

	public String getEmailRefcode() {
		return emailRefcode;
	}

	public void setEmailRefcode(String emailRefcode) {
		this.emailRefcode = emailRefcode;
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

	public void setEmaildonations(List<Donation> Emaildonations) {
		this.Emaildonations = Emaildonations;
	}

	public String getEmailDateFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	return df.format(this.Emaildate);
    }
    public String getEmailTimeFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("kk:mm");
    	return df.format(this.Emailtime);
    }
    
	public User getEmail_uploader() {
		return email_uploader;
	}

	public void setEmail_uploader(User email_uploader) {
		this.email_uploader = email_uploader;
	}
	public Double getEmailSum() {
		List<Donation> contributions = this.getEmaildonations();
		Double sum = 0.0;
		if (contributions.size() > 0) {
			for (int i = 0; i < contributions.size(); i++) {
				sum += contributions.get(i).getAmount();
			}
		}
		return sum;
	}
	
	public Double getEmailAverage() {
		List<Donation> contributions = this.getEmaildonations();
		Double sum = 0.0;
		Double emailAverage = 0.0;
		if (contributions.size() > 0) {
			for (int i = 0; i < contributions.size(); i++) {
				sum += contributions.get(i).getAmount();
			}
			emailAverage = sum/contributions.size();
		}
		return emailAverage;
	}

	public void setEmailAverage(Double emailAverage) {
		this.emailAverage = emailAverage;
	}

	public String getEmailAverageFormatted() {
		if (this.emailAverage == null) {
			this.emailAverage = 0.0;
		}
		double emailAverage1 = (double) getEmailAverage();
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(emailAverage1);
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
