package com.techelevator.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Mailer;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class UserController {

	private UserDAO userDAO;

	@Autowired
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}
			
		userDAO.saveUser(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPhoneNumber(), user.getPassword(), user.getEmail());
		return "redirect:/login";
	}
	
	@RequestMapping(path="/requestPasswordReset")
	public String requestPasswordReset() {
		return "requestPasswordReset";
	}
	
	@RequestMapping(path="/passwordResetRequestForm", method=RequestMethod.POST)
	public String passwordResetRequestForm(@RequestParam String email) {
		String userName = userDAO.getUserNameByEmail(email);
		String resetKey = userDAO.getPasswordResetKey(userName);
		Mailer mail = new Mailer(userName, email);
		mail.passwordResetTemplate(resetKey);
		try {
			mail.send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/login";
	}
	
	@RequestMapping(path="/resetPassword", method=RequestMethod.GET)
	public String showResetPasswordPage(ModelMap model, @RequestParam String userName, @RequestParam String resetKey) {
		if (userDAO.isResetPasswordKeyValid(userName, resetKey)) {
			model.addAttribute("userName", userName);
			model.addAttribute("resetKey", resetKey);
			return "resetPassword";
		}
		return "redirect:/login";
		
	}
	
	@RequestMapping(path="/submitResetPassword", method=RequestMethod.POST)
	public String resetPassword(@RequestParam String userName, @RequestParam String resetKey, @RequestParam String newPassword) {
		if (userDAO.isResetPasswordKeyValid(userName, resetKey)) {
			userDAO.updatePassword(userName, newPassword);
		}
		return "redirect:/login";
	}
	
	
}
