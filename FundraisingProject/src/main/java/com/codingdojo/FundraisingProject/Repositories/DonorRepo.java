package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Donor;

@Repository
public interface DonorRepo extends CrudRepository<Donor, Long>{
	List<Donor> findAll();
	Donor findBydonorEmail(String email);
	
	//average functions
	@Query(value = "SELECT AVG(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Double donoraverages(@Param("donorid") Long id);
	
	//sum functions
	@Query(value = "SELECT SUM(donations.amount) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Double donorsums(@Param("donorid") Long id);
	
	//donation count functions
	@Query(value = "SELECT COUNT(DISTINCT donations.id) FROM donors LEFT JOIN donations ON donations.donor_id = donors.id WHERE donors.id = :donorid", nativeQuery = true)
	Integer donordoncount(@Param("donorid") Long id);
}