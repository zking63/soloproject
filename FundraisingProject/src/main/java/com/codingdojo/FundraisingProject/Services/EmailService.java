package com.codingdojo.FundraisingProject.Services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Data;
import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Emails;
import com.codingdojo.FundraisingProject.Repositories.DataRepo;
import com.codingdojo.FundraisingProject.Repositories.DonationRepo;
import com.codingdojo.FundraisingProject.Repositories.EmailRepo;

@Service
public class EmailService {
	@Autowired
	private EmailRepo erepo;
	
	@Autowired
	private DataRepo datarepo;
	
	@Autowired
	private DonationRepo drepo;
	
	public Emails createEmail(Emails email) {
		return erepo.save(email);
	}
	
	public List<Emails> allEmails(){
		return erepo.findAll();
	}
	
	public Emails findEmailbyId(long id) {
		return erepo.findById(id).orElse(null);
	}
	
	public Emails findEmailbyRefcode(String emailRefcode) {
		return erepo.findByemailRefcode(emailRefcode);
	}
	public void delete(long id) {
		erepo.deleteById(id);
	}
	public List<Donation> getEmailDonations(Emails email){
		Long id = email.getId();
		return drepo.findByemailDonation(id);
	}
	public List<Emails> EmailTest(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> EmailTestAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByOrderByAsc(startdateE, enddateE);
	}
	public List<Data> getEmailDatatest() {
		List <Data> averages = erepo.averagestest();
		return averages;
	}
	public Data getEmailData(Emails email) {
		Data emaildata = email.getEmaildata();
		Long id = email.getId();
		Double esum = erepo.sums(id);
		Double eaverage = erepo.averages(id);
		Integer donationscount = erepo.donationscount(id);
		if (emaildata == null){
			Data emaildata1 = new Data(email, eaverage, esum);
			return datarepo.save(emaildata1);
		}
		else {
			emaildata.setEmailsum(esum);
			emaildata.setDonationcount(donationscount);
			emaildata.setEmailAverage(eaverage);
			System.out.println(esum);
			System.out.println(eaverage);
			return datarepo.save(emaildata);
		}
		/*else if (emaildata.getEmailAverage() == null || emaildata.getEmailsum() == null) {
			Data emaildata1 = new Data(email, eaverage, esum);
			return datarepo.save(emaildata1);
		}
		/*else if (emaildata.getEmailsum() == null) {
			Data emaildata1 = new Data(email, eaverage, esum);
			return datarepo.save(emaildata1);
		}*/
	}
	/*public Data getEmailSum(Emails email) {
		Data emaildata = email.getEmaildata();
		Long id = email.getId();
		Double eaverage = erepo.averages(id);
		Double esum = erepo.sums(id);
		if (emaildata == null){
			Data emaildata1 = new Data(email, eaverage, esum);
			return datarepo.save(emaildata1);
		}
		else {
			emaildata.setEmailsum(esum);
			emaildata.setEmailAverage(eaverage);
			return datarepo.save(emaildata);
		}
	}*/
	public List<Emails> AvDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByAverageOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> AverageAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByAverageOrderByAsc(startdateE, enddateE);
	}
	/*public Double getAv(@Param("id") Long id) {
		return erepo.averages(id);
	}*/
}
