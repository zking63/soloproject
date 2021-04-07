package com.codingdojo.FundraisingProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.Emails;
import com.codingdojo.FundraisingProject.Repositories.EmailRepo;

@Service
public class EmailService {
	@Autowired
	private EmailRepo erepo;
	
	public Emails createEmail(Emails email) {
		return erepo.save(email);
	}
}
