package com.codingdojo.FundraisingProject.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.FundraisingProject.Models.User;
import com.codingdojo.FundraisingProject.Repositories.UserRepo;

@Component
public class UserValidation implements Validator{
	@Autowired
	private UserRepo urepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if(this.urepo.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "Unique");
        }
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        } 
		
	}
}
