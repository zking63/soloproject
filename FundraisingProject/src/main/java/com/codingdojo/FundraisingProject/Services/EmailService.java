package com.codingdojo.FundraisingProject.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Emails;
import com.codingdojo.FundraisingProject.Repositories.EmailRepo;

@Service
public class EmailService {
	@Autowired
	private EmailRepo erepo;
	
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
	/*public Double getEmailSum(Emails email) {
		List<Donation> contributions = email.getEmaildonations();
		Double sum = 0.0;
		if (contributions.size() > 0) {
			for (int i = 0; i < contributions.size(); i++) {
				sum += contributions.get(i).getAmount();
			}
		}
		return sum;
	}
	public Double getEmailAverage(Emails email) {
		List<Donation> contributions = email.getEmaildonations();
		Double sum = 0.0;
		Double average = 0.0;
		if (contributions.size() > 0) {
			for (int i = 0; i < contributions.size(); i++) {
				sum += contributions.get(i).getAmount();
			}
			average = sum/contributions.size();
		}
		return average;
	}*/
}
