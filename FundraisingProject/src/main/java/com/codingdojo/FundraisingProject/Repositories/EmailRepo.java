package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.Emails;

@Repository
public interface EmailRepo extends CrudRepository<Emails, Long>{
	List<Emails> findAll();
	Emails findByemailRefcode(String emailRefcode);
}
