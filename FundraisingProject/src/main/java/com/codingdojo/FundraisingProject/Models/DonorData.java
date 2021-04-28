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
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="datadonor_id")
    private Donor datadonor;
    
    public DonorData() {
    	
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
	
    
}
