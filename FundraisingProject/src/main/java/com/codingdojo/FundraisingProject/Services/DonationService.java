package com.codingdojo.FundraisingProject.Services;

import java.util.List;

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
	public List<Donation> findByUser(Long user_id) {
		return donrepo.findBydonor(user_id);
	}
	public Donor getDonorbyDonation(Donation donation) {
		return donation.getDonor();
	}
}
