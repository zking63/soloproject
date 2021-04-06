package com.codingdojo.FundraisingProject.Services;

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
}
