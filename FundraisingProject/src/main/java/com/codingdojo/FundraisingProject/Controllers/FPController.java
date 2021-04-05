package com.codingdojo.FundraisingProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.FundraisingProject.Models.User;
import com.codingdojo.FundraisingProject.Services.UserService;

@Controller
public class FPController {
	@Autowired
	private UserService uservice;
	@RequestMapping("/")
	public String index(@ModelAttribute("user")User user) {
		return "loginreg.jsp";
	}
}
