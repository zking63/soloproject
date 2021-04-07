package com.codingdojo.FundraisingProject.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Repositories.DonorRepo;


@Service
public class DonorService {
	@Autowired
	private DonorRepo drepo;
	
	public Donor createDonor(Donor donor) {
		return drepo.save(donor);
	}
	public List<Donor> allDonors() {
		return drepo.findAll();
	}
	public Donor findbyId(Long id) {
		return drepo.findById(id).orElse(null);
	}
	public Date getMostRecentdonation(Long id) {
	Donor donor = drepo.findById(id).orElse(null);
	List<Donation> contributions = donor.getContributions();
	List<Date> dates = new ArrayList<Date>();
	if (contributions.size() > 0) {
		for (int i = 0; i < contributions.size(); i++) {
			dates.add(contributions.get(i).getDondate());
			}
	}
	Date mostRecent = Collections.max(dates);
	return mostRecent;
}
}
