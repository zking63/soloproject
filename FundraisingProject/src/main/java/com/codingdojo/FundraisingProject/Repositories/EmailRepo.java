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
	@Query(value = "SELECT * FROM emails where emails.Emaildate >= :startdateE and emails.Emaildate <= :enddateE order by emails.Emaildate Desc, emails.Emailtime Desc", nativeQuery = true)
	List<Emails> findByOrderByDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, 
			@Param("enddateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE);
	@Query(value = "SELECT * FROM emails where emails.Emaildate >= :startdateE and emails.Emaildate <= :enddateE order by emails.Emaildate Asc, emails.Emailtime Asc", nativeQuery = true)
	List<Emails> findByOrderByAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, 
			@Param("enddateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE);
	@Query(value = "SELECT * FROM emails where emails.Emaildate >= :startdateE and emails.Emaildate <= :enddateE order by emails.email_average Desc", nativeQuery = true)
	List<Emails> findByAverageOrderByDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, 
			@Param("enddateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE);
	@Query(value = "SELECT * FROM emails where emails.Emaildate >= :startdateE and emails.Emaildate <= :enddateE order by emails.email_average Asc", nativeQuery = true)
	List<Emails> findByAverageOrderByAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, 
			@Param("enddateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE);
	@Query(value = "SET @email_average = (SELECT AVG(donations.amount) FROM emails LEFT JOIN donations ON (donations.email_id = emails.id)\n WHERE emails.id = emailid)", nativeQuery = true)
	List<Emails> findByAverage(@Param("emailid") long id);
}
