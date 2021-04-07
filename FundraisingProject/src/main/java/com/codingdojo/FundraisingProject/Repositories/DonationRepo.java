package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Donation;

@Repository
public interface DonationRepo extends CrudRepository<Donation, Long>{
	List<Donation> findAll();
	List<Donation> findBydonor(Long donor_id);
	List<Donation> findByemailDonation(Long email_id);
	//@Query(value = "SELECT donations from fundraising order by donations.amount asc")
	//List<Donation> findAllByOrderByAmountAsc(List<Donation> donations);
}