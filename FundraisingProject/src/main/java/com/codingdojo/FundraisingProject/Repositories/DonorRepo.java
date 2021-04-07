package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;

@Repository
public interface DonorRepo extends CrudRepository<Donor, Long>{
	List<Donor> findAll();
}