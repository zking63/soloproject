package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Models.Emails;

@Repository
public interface DonorRepo extends CrudRepository<Donor, Long>{
	List<Donor> findAll();
	Donor findBydonorEmail(String email);
	
	//date functions
	@Query(value = "SELECT * FROM donors where mostrecent_date >= :startdate and mostrecent_date <= :enddate order by mostrecent_date Desc, mostrecenttime Desc", nativeQuery = true)
	List <Donor> findAllWithMostRecent(@Param("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") String enddate);
	
	@Query(value = "SELECT * FROM donors where mostrecent_date >= :startdate and mostrecent_date <= :enddate order by mostrecent_date Asc, mostrecenttime Asc", nativeQuery = true)
	List <Donor> findAllWithMostRecentDondateAfterAsc(@Param("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") String enddate);
	
	//average functions
	@Query(value = "SELECT AVG(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Double donoraverages(@Param("donorid") Long id);

	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donoraverage DESC", nativeQuery = true)
	List<Donor> findByDonorAverageByDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donoraverage ASC", nativeQuery = true)
	List<Donor> findByDonorAverageByAsc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//average within range functions
	@Query(value = "SELECT AVG(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid AND donations.dondate >= :startdate and donations.dondate <= :enddate AND donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate", nativeQuery = true)
	Double donoravgRange(@Param("donorid") Long id, @Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//sum functions
	@Query(value = "SELECT SUM(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Double donorsums(@Param("donorid") Long id);
	
	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donorsum ASC", nativeQuery = true)
	List<Donor> findByDonorsumByAsc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donorsum DESC", nativeQuery = true)
	List<Donor> findByDonorsumByDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//sum within rage functions
	@Query(value = "SELECT SUM(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid AND donations.dondate >= :startdate and donations.dondate <= :enddate AND donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate", nativeQuery = true)
	Double donorsumRange(@Param("donorid") Long id, @Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//donation count functions
	@Query(value = "SELECT COUNT(DISTINCT donations.id) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Integer donordoncount(@Param("donorid") Long id);
	
	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donor_contributioncount DESC", nativeQuery = true)
	List<Donor> findByContributionCountByDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	@Query(value = "SELECT * FROM donors LEFT JOIN data_donors ON donors.id = data_donors.donor_id where donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate ORDER BY data_donors.donor_contributioncount ASC", nativeQuery = true)
	List<Donor> findByContributionCountByAsc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//count within range function 
	@Query(value = "SELECT COUNT(DISTINCT donations.id) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid AND donations.dondate >= :startdate and donations.dondate <= :enddate AND donors.mostrecent_date >= :startdate and donors.mostrecent_date <= :enddate", nativeQuery = true)
	Integer donordoncountRange(@Param("donorid") Long id, @Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
	
	//most recent donation function
	@Query(value = "SELECT donations.id FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid ORDER BY donations.Dondate DESC, donations.Dontime DESC LIMIT 1", nativeQuery = true)
	Long mostRecentDonationDate(@Param("donorid") Long id);
	
	@Query(value = "SELECT * FROM donors where mostrecent_date >= :startdate and mostrecent_date <= :enddate order by mostrecentamount Desc", nativeQuery = true)
	List <Donor> MostrecentamountSortDesc(@Param("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") String enddate);
	
	@Query(value = "SELECT * FROM donors where mostrecent_date >= :startdate and mostrecent_date <= :enddate order by mostrecentamount Asc", nativeQuery = true)
	List <Donor> MostrecentamountSortAsc(@Param("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") String enddate);
}