package com.codingdojo.FundraisingProject.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
	private String timeFormat() {
		SimpleDateFormat df = new SimpleDateFormat("kk:mm");
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
		 model.addAttribute("email", this.eservice.allEmails());
		 model.addAttribute("dateFormat", dateFormat2());
		 return "newdonation.jsp";
	 }
	 @PostMapping(value="/newdonation")
	 public String CreateDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (result.hasErrors()) {
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 model.addAttribute("donor", this.dservice.allDonors());
			 model.addAttribute("email", this.eservice.allEmails());
			 model.addAttribute("dateFormat", dateFormat());
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
		 return "redirect:/emails";
	 }
	 @RequestMapping("/emails")
	 public String Emailpage(@ModelAttribute("email") Emails email, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("email", this.eservice.allEmails());
		 //model.addAttribute("sum", this.eservice.getEmailSum(email));
		 //model.addAttribute("average", this.eservice.getEmailAverage(email));
		 return "emails.jsp";
	 }
	 @RequestMapping("/donors/{id}")
	 public String showDonor(@PathVariable("id") long id, Model model, HttpSession session, @ModelAttribute("donor")Donor donor) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("donor", this.dservice.findbyId(id));
		 return "/donors/showdonor.jsp";
	 }
	@RequestMapping("/donors/delete/{id}")
	public String DeleteDonor(@PathVariable("id") Long id, HttpSession session, Model model) {
		this.dservice.delete(id);
		return "redirect:/donors";
	}
	@RequestMapping(value="/donors/edit/{id}")
	public String EditDonor(@PathVariable("id") long id, HttpSession session, Model model) {
		Long user_id = (Long)session.getAttribute("user_id");
		if (user_id == null) {
			return "redirect:/";
		}
		User user = uservice.findUserbyId(user_id);
		model.addAttribute("donor", dservice.findbyId(id));
		model.addAttribute("user", user);
		//dservice.createDonor(dservice.findbyId(id));
		return "/donors/editdonor.jsp";
	}
	 @RequestMapping(value="/donors/edit/{id}", method=RequestMethod.POST)
	 public String UpdateDonor(@Valid @ModelAttribute("donor") Donor donor, BindingResult result, Model model, HttpSession session) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (result.hasErrors()) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 dservice.createDonor(donor);
		 return "redirect:/donors";
	 }
	 @RequestMapping("/emails/{id}")
	 public String showEmail(@PathVariable("id") long id, Model model, HttpSession session, @ModelAttribute("email")Emails email) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 model.addAttribute("emails", this.eservice.findEmailbyId(id));
		 return "/emails/showemail.jsp";
	 }
	 @RequestMapping("/home")
	 public String homePage(Model model, HttpSession session, @ModelAttribute("donations")Donation donation,
			 @Param("startdate") @DateTimeFormat(iso = ISO.DATE) String startdate, 
			 @Param("enddate") @DateTimeFormat(iso = ISO.DATE) String enddate) {
		 Long user_id = (Long)session.getAttribute("user_id");
		 if (user_id == null) {
			 return "redirect:/";
		 }
		 User user = uservice.findUserbyId(user_id);
		 model.addAttribute("user", user);
		 //model.addAttribute("donations", this.donservice.findDonations());
		 model.addAttribute("dateFormat", dateFormat());
		 model.addAttribute("donations", donservice.DonTest(startdate, enddate));
		 return "home.jsp";
	 }
		@RequestMapping("/emails/delete/{id}")
		public String DeleteEmail(@PathVariable("id") Long id, HttpSession session, Model model) {
			this.eservice.delete(id);
			return "redirect:/emails";
		}
		@RequestMapping(value="/emails/edit/{id}")
		public String EditEmail(@PathVariable("id") long id, HttpSession session, Model model) {
			Long user_id = (Long)session.getAttribute("user_id");
			if (user_id == null) {
				return "redirect:/";
			}
			User user = uservice.findUserbyId(user_id);
			model.addAttribute("email", eservice.findEmailbyId(id));
			model.addAttribute("user", user);
			return "/emails/editemail.jsp";
		}
		 @RequestMapping(value="/emails/edit/{id}", method=RequestMethod.POST)
		 public String UpdateEmail(@Valid @ModelAttribute("email") Emails email, BindingResult result, Model model, HttpSession session) {
			 Long user_id = (Long)session.getAttribute("user_id");
			 if (result.hasErrors()) {
				 return "redirect:/";
			 }
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 eservice.createEmail(email);
			 return "redirect:/emails";
		 }
		 //edit and delete donations homepage
			@RequestMapping("/donations/delete/{id}")
			public String DeleteDonation(@PathVariable("id") Long id, HttpSession session, Model model) {
				this.donservice.delete(id);
				return "redirect:/home";
			}
			@RequestMapping(value="/donations/edit/{id}")
			public String EditDonation(@PathVariable("id") long id, HttpSession session, Model model) {
				Long user_id = (Long)session.getAttribute("user_id");
				if (user_id == null) {
					return "redirect:/";
				}
				User user = uservice.findUserbyId(user_id);
				model.addAttribute("donation", donservice.findDonationbyId(id));
				model.addAttribute("user", user);
				model.addAttribute("donor", this.dservice.allDonors());
				model.addAttribute("email", this.eservice.allEmails());
				model.addAttribute("dateFormat", dateFormat2());
				return "/donations/editdonation.jsp";
			}
		 @RequestMapping(value="/donations/edit/{id}", method=RequestMethod.POST)
		 public String UpdateDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result, Model model, HttpSession session) {
			 Long user_id = (Long)session.getAttribute("user_id");
			 if (result.hasErrors()) {
				 return "redirect:/";
			 }
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 model.addAttribute("donor", this.dservice.allDonors());
			 model.addAttribute("email", this.eservice.allEmails());
			 model.addAttribute("dateFormat", dateFormat2());
			 donservice.createDonation(donation);
			 return "redirect:/home";
		 }
		 //edit and delete donations donor page
			@RequestMapping("/donations/delete/{id}/donor")
			public String DeleteDonationfromDonorPage(@PathVariable("id") Long id, HttpSession session, Model model) {
				Donor donor = this.donservice.findDonationbyId(id).getDonor();
				long donorid = donor.getId();
				this.donservice.delete(id);
				return "redirect:/donors/" + donorid;
			}
			/*@RequestMapping(value="/donations/edit/{id}")
			public String EditDonation(@PathVariable("id") long id, HttpSession session, Model model) {
				Long user_id = (Long)session.getAttribute("user_id");
				if (user_id == null) {
					return "redirect:/";
				}
				User user = uservice.findUserbyId(user_id);
				model.addAttribute("donation", donservice.findDonationbyId(id));
				model.addAttribute("user", user);
				model.addAttribute("donor", this.dservice.allDonors());
				model.addAttribute("email", this.eservice.allEmails());
				model.addAttribute("dateFormat", dateFormat2());
				return "/donations/editdonation.jsp";
			}
		 @RequestMapping(value="/donations/edit/{id}", method=RequestMethod.POST)
		 public String UpdateDonation(@Valid @ModelAttribute("donation") Donation donation, BindingResult result, Model model, HttpSession session) {
			 Long user_id = (Long)session.getAttribute("user_id");
			 if (result.hasErrors()) {
				 return "redirect:/";
			 }
			 User user = uservice.findUserbyId(user_id);
			 model.addAttribute("user", user);
			 model.addAttribute("donor", this.dservice.allDonors());
			 model.addAttribute("email", this.eservice.allEmails());
			 model.addAttribute("dateFormat", dateFormat2());
			 donservice.createDonation(donation);
			 return "redirect:/home";
		 }*/
		/*private String dateFormat3() {
			long df;
			java.sql.Date dateDB = new java.sql.Date(df);
			return df.format(new Date());
		}*/
		 /*@RequestMapping("/test")
		 public String Test( Model model, @Param("startdate") @DateTimeFormat(iso = ISO.DATE) String startdate, 
				 @Param("enddate") @DateTimeFormat(iso = ISO.DATE) String enddate) {
			 model.addAttribute("dateFormat", dateFormat());
			 model.addAttribute("donations", donservice.DonTest(startdate, enddate));
			 return "test.jsp";
		 }*/
		 //sorting
		 @RequestMapping(value="/sortup")
		 public String SortUp(Model model) {
			 List<Donation> donations = this.donservice.orderAmounts();
			 model.addAttribute("donations", donations);
			 return "home.jsp";
		 }
		 @RequestMapping(value="/sortdown")
		 public String SortDown(Model model, @Param("startdate") @DateTimeFormat(iso = ISO.DATE) String startdate, 
					@Param("enddate") @DateTimeFormat(iso = ISO.DATE) String enddate) {
			 List<Donation> donations = this.donservice.orderAmounts2(startdate, enddate);
			 model.addAttribute("donations", donations);
			 model.addAttribute("dateFormat", dateFormat());
			 return "home.jsp";
		 }
		 //all sorting
}
