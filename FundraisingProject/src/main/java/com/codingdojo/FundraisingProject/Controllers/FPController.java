package com.codingdojo.FundraisingProject.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Models.User;
import com.codingdojo.FundraisingProject.Services.DonorService;
import com.codingdojo.FundraisingProject.Services.UserService;
import com.codingdojo.FundraisingProject.Validation.UserValidation;

@Controller
public class FPController {
	@Autowired
	private UserService uservice;
	
	@Autowired
	private UserValidation uvalidation;
	
	@Autowired
	private DonorService dservice;
	
	@RequestMapping("/")
	public String index(@ModelAttribute("user")User user) {
		return "loginreg.jsp";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		uvalidation.validate(user, result);
		if (result.hasErrors()) {
			return "loginreg.jsp";
		}
		User newUser = uservice.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/home";
	}
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	 public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirs) {
	     // if the user is authenticated, save their user id in session
		 boolean isAuthenticated = uservice.authenticateUser(email, password);
		 if(isAuthenticated) {
			 User u = uservice.findUserbyEmail(email);
			 session.setAttribute("user_id", u.getId());
			 return "redirect:/home";
		 }
	     // else, add error messages and return the login page
		 else {
			 redirs.addFlashAttribute("error", "Invalid Email/Password");
			 return "redirect:/";
		 }
	 }
	 
	 @RequestMapping("/logout")
	 public String logout(HttpSession session) {
	     // invalidate session
		 session.invalidate();
	     // redirect to login page
		 return "redirect:/";
	 }
	 @RequestMapping("/newdonor")
	 public String newDonorPage(@ModelAttribute("donor") Donor donor, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 return "createDonor.jsp";
	 }
	 @RequestMapping(value="/newdonor", method=RequestMethod.POST)
	 public String CreateDonor(@Valid @ModelAttribute("donor") Donor donor, BindingResult result, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (result.hasErrors()) {
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 return "createDonor.jsp";
		 }
		 dservice.createDonor(donor);
		 return "redirect:/donors";
	 }
	 @RequestMapping("/donors")
	 public String donorsPage(@ModelAttribute("donor") Donor donor, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("donor", donor);
		 return "donors.jsp";
	 }
}
