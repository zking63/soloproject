package com.codingdojo.FundraisingProject.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="data_funds")
public class Data {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private Double emailaverage;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="email_id")
    private Emails dataemail;
    
    
	public Data() {

	}
	
	public Data(Emails email, Double eaverage) {
		this.dataemail = email;
		this.emailaverage = eaverage;
	}
	
	
	
	public Double getEmailAverage() {
		return emailaverage;
	}

	public void setEmailAverage(Double emailaverage) {
		this.emailaverage = emailaverage;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Emails getDataEmail() {
		return dataemail;
	}
	public void setDataEmail(Emails dataemail) {
		this.dataemail = dataemail;
	}
    
}
