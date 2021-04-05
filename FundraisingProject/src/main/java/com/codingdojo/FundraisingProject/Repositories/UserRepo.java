package com.codingdojo.FundraisingProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.FundraisingProject.Models.User;

@Repository
public interface UserRepo extends CrudRepository <User, Long>{
	User findByEmail(String email);
}
