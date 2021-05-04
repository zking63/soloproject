package com.codingdojo.FundraisingProject.Models;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import org.springframework.format.annotation.DateTimeFormat;

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
    
	@OneToOne(mappedBy="datadonor", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private DonorData donordata;
	
    /*@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="donation_id")
    private Donation mostrecentDonationbyDonor;*/
    
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date mostrecentDate;
    
	@DateTimeFormat(pattern ="kk:mm")
	private Date mostrecenttime;
	
	private Double mostrecentamount;
	
	//within range functions
	private Integer countwithinrange;
	private Double sumwithinrange;
	private Double averagewithinrange;
    
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

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}

	public DonorData getDonordata() {
		return donordata;
	}

	public void setDonordata(DonorData donordata) {
		this.donordata = donordata;
	}

	/*public Donation getMostrecentDonationbyDonor() {
		return mostrecentDonationbyDonor;
	}

	public void setMostrecentDonationbyDonor(Donation mostrecentDonationbyDonor) {
		this.mostrecentDonationbyDonor = mostrecentDonationbyDonor;
	}*/

	public Date getMostrecentDate() {
		return mostrecentDate;
	}

	public void setMostrecentDate(Date mostrecentDate) {
		this.mostrecentDate = mostrecentDate;
	}

	public Date getMostrecenttime() {
		return mostrecenttime;
	}

	public void setMostrecenttime(Date mostrecenttime) {
		this.mostrecenttime = mostrecenttime;
	}

	public Double getMostrecentamount() {
		return mostrecentamount;
	}

	public void setMostrecentamount(Double mostrecentamount) {
		this.mostrecentamount = mostrecentamount;
	}
	
    public String getRecentDateFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	return df.format(this.mostrecentDate);
    }
    public String getRecentTimeFormatted() {
    	SimpleDateFormat df = new SimpleDateFormat("kk:mm");
    	return df.format(this.mostrecenttime);
    }
	public String getDonorRecentAmountFormatted() {
		if (this.mostrecentamount == null) {
			this.mostrecentamount = 0.0;
		}
		double mostrecentamount1 = (double) getMostrecentamount();
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(mostrecentamount1);
	}

	public Integer getCountwithinrange() {
		return countwithinrange;
	}

	public void setCountwithinrange(Integer countwithinrange) {
		this.countwithinrange = countwithinrange;
	}

	public Double getSumwithinrange() {
		return sumwithinrange;
	}

	public void setSumwithinrange(Double sumwithinrange) {
		this.sumwithinrange = sumwithinrange;
	}

	public Double getAveragewithinrange() {
		return averagewithinrange;
	}

	public void setAveragewithinrange(Double averagewithinrange) {
		this.averagewithinrange = averagewithinrange;
	}
	
	
}
