package com.codingdojo.FundraisingProject.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Repositories.DonationRepo;

@Service
public class DonationService {
	@Autowired
	private DonationRepo donrepo;
	
	public Donation createDonation(Donation d) {
		return donrepo.save(d);
	}
	
	public List<Donation> findDonations(){
		return donrepo.findAll();
	}
	
	public Donation findDonationbyId(long id) {
		return donrepo.findById(id).orElse(null);
	}
	public List<Donation> findByDonor(long donor_id) {
		return donrepo.findBydonor(donor_id);
	}
	public List<Donation> findByEmail(long email_id) {
		return donrepo.findByemailDonation(email_id);
	}
	/*public Donation mostRecentDonation(Donor donor) {
		Donation contribution = donor.mostRecentDonation(donor);
		//Donation mostRecent = contributions.get(0);
		//for (int i = 0; i < contributions.size(); i++) {
			//if (contributions.get(i).getId() > mostRecent.getId()) {
				//mostRecent = contributions.get(i);
			//}
		//}
		return contribution;
	}*/
	public List<Donation> orderAmounts(){
		return donrepo.findAllByOrderByAmountAsc();
	}
	public List<Donation> orderAmounts2(){
		return donrepo.findAllByOrderByAmountDesc();
	}
	public void delete(long id) {
		donrepo.deleteById(id);
	}
	public List<Donation> DonTest(@Param("startdate") Date startdate, @Param("enddate") Date enddate){
		/*java.sql.Date dateDB = new java.sql.Date(startdate.getTime());
		java.sql.Date dateE = new java.sql.Date(enddate.getTime());*/
		return donrepo.findAllWithDondateAfter(startdate, enddate);
		//return donrepo.findAllWithDondateAfter(dateDB, dateE);
	}
}
