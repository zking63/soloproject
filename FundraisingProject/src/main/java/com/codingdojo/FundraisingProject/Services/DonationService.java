package com.codingdojo.FundraisingProject.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Donation findDonationbyId(Long id) {
		return donrepo.findById(id).orElse(null);
	}
	public List<Donation> findByDonor(Long donor_id) {
		return donrepo.findBydonor(donor_id);
	}
	public List<Donation> findByEmail(Long email_id) {
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
}
