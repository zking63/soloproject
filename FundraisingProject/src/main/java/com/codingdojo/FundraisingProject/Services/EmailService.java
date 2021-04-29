package com.codingdojo.FundraisingProject.Services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Data;
import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.DonorData;
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
	
	public Emails updateEmail(Emails email) {
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
	public List<Data> getEmailDatatest() {
		List <Data> averages = erepo.averagestest();
		return averages;
	}
	public Data getEmailData(Emails email) {
		Data emaildata = email.getEmaildata();
		Long id = email.getId();
		Double esum = 0.00;
		Double eaverage = 0.00;
		Integer donationscount = 0;
		Integer donorscount = 0;
		List<Data> alldata = datarepo.findAll();
		if (emaildata == null) {
			emaildata = new Data(email, eaverage, esum, donationscount, donorscount);
			System.out.println(emaildata.getId());
			return datarepo.save(emaildata);
		}
		else {
			for (int i = 0; i < alldata.size(); i++) {
				System.out.println(alldata.get(i).getDataEmail().getEmailName());
				if (id == alldata.get(i).getDataEmail().getId()) {
					System.out.println("make ");
					Long edid = emaildata.getId();
					System.out.println("edid " + edid);
					edid = alldata.get(i).getId();
					System.out.println("edid " + edid);
					emaildata = datarepo.findById(edid).orElse(null);
					esum = erepo.sums(id);
					eaverage = erepo.averages(id);
					donationscount = erepo.donationscount(id);
					donorscount = erepo.donorscount(id);
					emaildata.setEmailsum(esum);
					emaildata.setDonationcount(donationscount);
					emaildata.setDonorcount(donorscount);
					emaildata.setEmailAverage(eaverage);
					System.out.println("edid " + edid);
					return datarepo.save(emaildata);
				}
				/*else {
					i++;
					System.out.println(alldata.get(i).getDataEmail().getEmailName());
				}*/
			}
			System.out.println("emaildata");
			return datarepo.save(emaildata);
		}
	}
	//sorting
	//date/time
	public List<Emails> EmailTest(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> EmailTestAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByOrderByAsc(startdateE, enddateE);
	}
	//averages
	public List<Emails> AvDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByAverageOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> AverageAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByAverageOrderByAsc(startdateE, enddateE);
	}
	//sums
	public List<Emails> SumDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findBySumOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> SumAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findBySumOrderByAsc(startdateE, enddateE);
	}
	//donation count
	public List<Emails> DonationsCountDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByDonationsCountOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> DonationsCountAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByDonationsCountOrderByAsc(startdateE, enddateE);
	}
	//donor count 
	public List<Emails> DonorCountDesc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByDonorCountOrderByDesc(startdateE, enddateE);
	}
	public List<Emails> DonorCountAsc(@Param("startdateE") @DateTimeFormat(pattern ="yyyy-MM-dd") String startdateE, @Param("enddateE") 
	@DateTimeFormat(pattern ="yyyy-MM-dd") String enddateE){
		return erepo.findByDonorCountOrderByAsc(startdateE, enddateE);
	}
}
