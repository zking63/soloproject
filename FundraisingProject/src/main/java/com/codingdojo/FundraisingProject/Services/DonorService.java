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
import com.codingdojo.FundraisingProject.Models.DonorData;
import com.codingdojo.FundraisingProject.Repositories.DonorDataRepo;
import com.codingdojo.FundraisingProject.Repositories.DonorRepo;


@Service
public class DonorService {
	@Autowired
	private DonorRepo drepo;
	
	@Autowired
	private DonorDataRepo dondrepo;
	
	public Donor createDonor(Donor donor) {
		return drepo.save(donor);
	}
	public Donor updateDonor(Donor donor) {
		return drepo.save(donor);
	}
	public Donor findDonorbyEmail(String email) {
		return drepo.findBydonorEmail(email);
	}
	public List<Donor> allDonors() {
		return drepo.findAll();
	}
	public Donor findbyId(long id) {
		return drepo.findById(id).orElse(null);
	}
	public void delete(long id) {
		drepo.deleteById(id);
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
	public List<Donation> getDonationsbydonor(Long id){
		Donor donor = drepo.findById(id).orElse(null);
		List<Donation> contributions = donor.getContributions();
		return contributions;
	}
	
	public DonorData getDonorData(Donor donor) {
		DonorData donordata = donor.getDonordata();
		Long id = donor.getId();
		Double daverage = 0.0;
		List<DonorData> allDonordata = dondrepo.findAll();
		if (donordata != null) {
			 for (int i = 0; i < allDonordata.size(); i++) {
					if (id == allDonordata.get(i).getDatadonor().getId()) {
						Long ddid = donordata.getId();
						ddid = allDonordata.get(i).getId();
						donordata = dondrepo.findById(ddid).orElse(null);
						daverage = drepo.donoraverages(id);
						donordata.setDonoraverage(daverage);
					}
					else {
						i++;
					}
				}
				return dondrepo.save(donordata);
		}
		else {
			daverage = drepo.donoraverages(id);
			donordata = new DonorData(donor, daverage);
			return dondrepo.save(donordata);
		}
	}
}
