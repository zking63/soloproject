package com.codingdojo.FundraisingProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.FundraisingProject.Repositories.DonorRepo;

@Service
public class DonorService {
	@Autowired
	private DonorRepo drepo;
}
