package com.codingdojo.FundraisingProject.Services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Models.User;
import com.codingdojo.FundraisingProject.Repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo urepo;
	
	//register user
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return urepo.save(user);
	}
}
