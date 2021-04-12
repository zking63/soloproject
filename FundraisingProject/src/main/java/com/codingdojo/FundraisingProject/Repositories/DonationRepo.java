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
	@Query(value = "SELECT * FROM donations where donations.Dondate >= :startdate and donations.Dondate < :enddate", nativeQuery = true)
	List <Donation> findAllWithDondateAfter(@Param("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date startdate, 
			@Param("enddate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate);
	/*@Query(value = "SELECT * FROM donations where donations.Dondate >= :dateDB and donations.Dondate < :dateE", nativeQuery = true)
	List <Donation> findAllWithDondateAfter(@Param("dateDB") Date dateDB, 
			@Param("dateE") Date dateE);*/
}