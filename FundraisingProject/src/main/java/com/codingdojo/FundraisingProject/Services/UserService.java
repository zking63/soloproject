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
	
	//find user by email
	public User findUserbyEmail(String email) {
		return urepo.findByEmail(email);
	}
	
	//find user by id
	public User findUserbyId(Long id) {
		return urepo.findById(id).orElse(null);
	}
	
	//authenticate user
	public boolean authenticateUser(String email, String password) {
		User user = urepo.findByEmail(email);
		if (user == null){
			return false;
		}
		else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			return false;
		}
	}
}
