package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Donation;

@Repository
public interface DonationRepo extends CrudRepository<Donation, Long>{
	List<Donation> findAll();
}