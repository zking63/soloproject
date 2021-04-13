package com.codingdojo.FundraisingProject.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Repositories.DonorRepo;

@Component
public class DonorValidation implements Validator{
	@Autowired
	private DonorRepo drepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Donor.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
        Donor donor = (Donor) target;
        
        if(this.drepo.findBydonorEmail(donor.getDonorEmail()) != null) {
        	errors.rejectValue("donorEmail", "Unique");
        }
	}
}
