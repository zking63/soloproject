package com.codingdojo.FundraisingProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo urepo;
}
