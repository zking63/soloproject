package com.codingdojo.FundraisingProject.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Models.DonorData;
import com.codingdojo.FundraisingProject.Repositories.DonationRepo;
import com.codingdojo.FundraisingProject.Repositories.DonorDataRepo;
import com.codingdojo.FundraisingProject.Repositories.DonorRepo;


@Service
public class DonorService {
	@Autowired
	private DonorRepo drepo;
	
	@Autowired
	private DonorDataRepo dondrepo;
	
	@Autowired
	private DonationRepo donationrepo;
	
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
		Double donorsum = 0.0;
		Integer donationcount = 0;
		Long mostrecent_donation_id = null;
		//Donation mostrecent = donor.getMostrecentDonationbyDonor();
		Date mostrecentDate = null;
		Date mostrecenttime = null;
		Double mostrecentamount = 0.0;
		List<DonorData> allDonordata = dondrepo.findAll();
		if (donordata != null) {
			 for (int i = 0; i < allDonordata.size(); i++) {
				 System.out.println(allDonordata.get(i).getDatadonor().getDonorFirstName());
					if (id == allDonordata.get(i).getDatadonor().getId()) {
						Long ddid = donordata.getId();
						System.out.println("donordata id " + ddid);
						ddid = allDonordata.get(i).getId();
						donordata = dondrepo.findById(ddid).orElse(null);
						daverage = drepo.donoraverages(id);
						donorsum = drepo.donorsums(id);
						donationcount = drepo.donordoncount(id);
						donordata.setDonoraverage(daverage);
						System.out.println("average " + daverage);
						donordata.setDonor_contributioncount(donationcount);
						System.out.println("contribution " + donationcount);
						donordata.setDonorsum(donorsum);
						System.out.println("sum " + donorsum);
						mostrecent_donation_id = drepo.mostRecentDonationDate(id);
						Donation mostrecent = donationrepo.findById(mostrecent_donation_id).orElse(null);
						mostrecentDate = mostrecent.getDondate();
						mostrecenttime = mostrecent.getDontime();
						mostrecentamount = mostrecent.getAmount();
						donor.setMostrecentDate(mostrecentDate);
						donor.setMostrecentamount(mostrecentamount);
						donor.setMostrecenttime(mostrecenttime);
						//donordata.setMostrecent_donation(mostrecent_donation_id);
						//donor.setMostrecentDonationbyDonor(mostrecent);
						System.out.println("date " + mostrecent_donation_id);
						return dondrepo.save(donordata);
					}
				}
			    System.out.println("make");
				return dondrepo.save(donordata);
		}
		else {
			System.out.println("new");
			daverage = drepo.donoraverages(id);
			donorsum = drepo.donorsums(id);
			donationcount = drepo.donordoncount(id);
			mostrecent_donation_id = drepo.mostRecentDonationDate(id);
			Donation mostrecent = donationrepo.findById(mostrecent_donation_id).orElse(null);
			mostrecentDate = mostrecent.getDondate();
			mostrecenttime = mostrecent.getDontime();
			mostrecentamount = mostrecent.getAmount();
			donor.setMostrecentDate(mostrecentDate);
			donor.setMostrecentamount(mostrecentamount);
			donor.setMostrecenttime(mostrecenttime);
			//donor.setMostrecentDonationbyDonor(mostrecent);
			donordata = new DonorData(donor, daverage, donorsum, donationcount);
			System.out.println(donordata.getId());
			return dondrepo.save(donordata);
		}
	}
	public List<Donor> orderMostRecentbyDonorDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate){
		return drepo.findAllWithMostRecent(startdate, enddate);
	}
	public List<Donor> orderMostRecentbyDonorAsc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate){
		return drepo.findAllWithMostRecentDondateAfterAsc(startdate, enddate);
	}
	public List<Donor> orderDonorCountDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate){
		return drepo.findByContributionCountByDesc(startdate, enddate);
	}
	public List<Donor> orderDonorCountAsc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate){
		return drepo.findByContributionCountByAsc(startdate, enddate);
	}
}
