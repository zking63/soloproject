package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.DonorData;

@Repository
public interface DonorDataRepo extends CrudRepository<DonorData, Long>{
	List<DonorData>findAll();
}
