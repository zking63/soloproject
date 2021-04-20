package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Data;



@Repository
public interface DataRepo extends CrudRepository<Data, Long>{
	List<Data>findAll();
}
