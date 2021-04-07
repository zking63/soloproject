package com.codingdojo.FundraisingProject.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.FundraisingProject.Models.Donation;
import com.codingdojo.FundraisingProject.Models.Donor;
import com.codingdojo.FundraisingProject.Models.Emails;
import com.codingdojo.FundraisingProject.Models.User;
import com.codingdojo.FundraisingProject.Services.DonationService;
import com.codingdojo.FundraisingProject.Services.DonorService;
import com.codingdojo.FundraisingProject.Services.EmailService;
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
	
	@Autowired
	private DonationService donservice;
	
	@Autowired
	private EmailService eservice;
	
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
	 @PostMapping(value="/newdonor")
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
		 model.addAttribute("donor", this.dservice.allDonors());
		 //model.addAttribute("mostRecent", this.dservice.getMostRecentdonation(donor.getId()));
		 //model.addAttribute("mostRecent", donor.getMostRecentdonation().getAmount());
		 return "donors.jsp";
	 }
	private String dateFormat() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}
	private String dateFormat2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd, kk:mm");
		return df.format(new Date());
	}
	 @RequestMapping("/newdonation")
	 public String donationsPage(@ModelAttribute("donation") Donation donation, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("donor", this.dservice.allDonors());
		 model.addAttribute("dateFormat", dateFormat());
		 return "newdonation.jsp";
	 }
	 @PostMapping(value="/newdonation")
	 public String CreateDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (result.hasErrors()) {
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 return "newdonation.jsp";
		 }
		 donservice.createDonation(donation);
		 return "redirect:/home";
	 }
	 @RequestMapping("/newemail")
	 public String newEmailpage(@ModelAttribute("email") Emails email, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("dateFormat", dateFormat2());
		 return "newemail.jsp";
	 }
	 @PostMapping(value="/newemail")
	 public String CreateDonation(@Valid @ModelAttribute("email") Emails email, BindingResult result, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (result.hasErrors()) {
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 return "newemail.jsp";
		 }
		 eservice.createEmail(email);
		 return "redirect:/home";
	 }
}
