package com.codingdojo.FundraisingProject.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;


import com.codingdojo.FundraisingProject.Models.Emails;

@Repository
public interface EmailRepo extends CrudRepository<Emails, Long>{
	List<Emails> findAll();
	Emails findByemailRefcode(String emailRefcode);
	@Query(value = "SELECT * FROM emails where emails.Emaildate >= :startdate and emails.Emaildate <= :enddate order by emails.Emaildate Desc, emails.Emailtime Desc", nativeQuery = true)
	List<Emails> findByOrderByDesc(@Param("startdate") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdate, 
			@Param("enddate") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddate);
}
