package com.codingdojo.FundraisingProject.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.FundraisingProject.Models.Donation;

@Repository
public interface DonationRepo extends CrudRepository<Donation, Long>{
	List<Donation> findAll();
	List<Donation> findBydonor(Long donor_id);
	List<Donation> findByemailDonation(Long email_id);
	//@Query(value = "SELECT donations ")
	List<Donation> findAllByOrderByAmountAsc();
	List<Donation> findAllByOrderByAmountDesc();
	//@Query(value ="select * from table donations where donations.Dondate >= 2020-04-07 16:09:00.000000", nativeQuery = true)
    //List<Donation> findAllByDonDate(@Param("Dondate") Date Dondate);
	//List findAllByDatetimeBetween(
			//Date dateTimeStart,
			//Date dateTimeEnd);

	@Query(value = "SELECT * FROM donations where donations.Dondate >= 'startdate'", nativeQuery = true)
	List <Donation> findAllWithDondateAfter(@RequestParam("startdate") Date startdate);
}