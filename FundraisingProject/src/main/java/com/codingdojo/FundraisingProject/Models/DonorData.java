package com.codingdojo.FundraisingProject.Models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="data_donors")
public class DonorData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double donoraverage;
	private Double donorsum;
	private Integer donor_contributioncount;
	//private Long mostrecent_donation_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="donor_id")
    private Donor datadonor;
    
    public DonorData() {
    	
    }
    

	public DonorData(Donor donor, Double donoraverage, Double donorsum, Integer donor_contributioncount) {
		this.datadonor = donor;
		this.donoraverage = donoraverage;
		this.donorsum = donorsum;
		this.donor_contributioncount = donor_contributioncount;
		//this.mostrecent_donation_id = mostrecent_donation_id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Donor getDatadonor() {
		return datadonor;
	}

	public void setDatadonor(Donor datadonor) {
		this.datadonor = datadonor;
	}

	public Double getDonoraverage() {
		return donoraverage;
	}

	public void setDonoraverage(Double donoraverage) {
		this.donoraverage = donoraverage;
	}


	public Double getDonorsum() {
		return donorsum;
	}


	public void setDonorsum(Double donorsum) {
		this.donorsum = donorsum;
	}


	public Integer getDonor_contributioncount() {
		return donor_contributioncount;
	}


	public void setDonor_contributioncount(Integer donor_contributioncount) {
		this.donor_contributioncount = donor_contributioncount;
	}


	/*public Long getMostrecent_donation_id() {
		return mostrecent_donation_id;
	}


	public void setMostrecent_donation(Long mostrecent_donation_id) {
		this.mostrecent_donation_id = mostrecent_donation_id;
	}*/
	
    
}
