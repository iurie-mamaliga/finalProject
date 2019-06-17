package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.EventDAO;
import com.techelevator.model.MatchmakingCompany;
import com.techelevator.model.MatchmakingCompanyDAO;
import com.techelevator.model.QuotesDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.security.AuthorizationFilter;

@Controller
public class DashboardController {

	private UserDAO userDAO;

	@Autowired
	public DashboardController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}	

	
	@Autowired(required=false)
	public MatchmakingCompanyDAO companyDAO;
	
	@Autowired(required=false)
	public EventDAO eventDAO;
	
	@Autowired(required=false)
	public QuotesDAO quotesDAO;
	
	@RequestMapping("/users/{userName}")
	public String displayUserDashboard(@PathVariable String userName, HttpSession session, ModelMap model) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isStudent()) {
			model.addAttribute("quote", quotesDAO.getQuote());
			return "students/home";
		}
		if (currentUser != null && currentUser.isAdmin()) {			
			model.addAttribute("allUsers", userDAO.getAllUsers());
			return "admins/home";
		}
		if (currentUser != null && currentUser.isGuest()) {
			return "redirect:/guest";
		}
		return "redirect:/";
	}
	
	@RequestMapping(path="/changeUserRole", method=RequestMethod.POST)
	public String changeUserRole(HttpSession session, @RequestParam String userName, @RequestParam String role ) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			userDAO.updateUserRole(userName, role);
		}
		return "redirect:/users/" + currentUser.getUserName();
	}
	
	//for admin, so we're lazy
	@RequestMapping(path="/deleteUser", method=RequestMethod.GET)
	public String deleteUser(HttpSession session, @RequestParam String userName) {
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			userDAO.deleteUser(userName);
		}
		return "redirect:/users/" + currentUser.getUserName();
	}
	
		
	@RequestMapping("/users/{userName}/events")
	public String showEventsPage(@PathVariable String userName, HttpSession session, ModelMap model){
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isStudent()) {
			model.addAttribute("next7", eventDAO.getNext7Events());
			return "students/events";
		}
		if (currentUser != null && currentUser.isAdmin()) {
			model.addAttribute("upcomingEvents", eventDAO.getAllEvents());
			return "admins/events";
		}
		if (currentUser != null && currentUser.isGuest()) {
			return "redirect:/guest";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/users/{userName}/matchmaking")
	public String showMatchmakingPage(@PathVariable String userName, HttpSession session, ModelMap model){
		User currentUser = (User) session.getAttribute("currentUser");
		
		if (currentUser != null && currentUser.isStudent()) {
			model.addAttribute("allCompanies", companyDAO.showAllCompanies());
			return "students/matchmaking";
		}
		if (currentUser != null && currentUser.isAdmin()) {
			List<MatchmakingCompany> all = new ArrayList<MatchmakingCompany>();
			all = companyDAO.showAllCompanies();
			
			model.addAttribute("allCompanies", companyDAO.showAllCompanies());
			return "admins/matchmaking";
		}
		if (currentUser != null && currentUser.isGuest()) {
			model.addAttribute("allCompanies", companyDAO.showAllCompanies());
			return "redirect:/guest";
		}
		model.addAttribute("allCompanies", companyDAO.showAllCompanies());
		return "redirect:/";
	}
	
	@RequestMapping(path="/deleteCompany", method=RequestMethod.POST)
	public String deleteCompany(@RequestParam String delete, HttpSession session, @RequestParam String rowId, @RequestParam String companyName,
					@RequestParam String repName, @RequestParam String repEmail, @RequestParam String roleName) { 

		User currentUser = (User) session.getAttribute("currentUser");
		
		if (delete.equals("deleteRep")) {
			System.out.println("delete rep");
			deleteCompanyRepresentative(session, rowId);
		} else if (delete.equals("deleteRole")) {
			System.out.println("delete role");
			deleteCompanyPosition(session, rowId);
		}else if(delete.equals("saveCompany")){
				System.out.println("company added");
				saveCompany(session, companyName);
		
		}else if(delete.equals("saveRep")){
			System.out.println("rep added");
			saveRepresentative(session, rowId, repName, repEmail);
	
		}else if(delete.equals("saveRole")){
			System.out.println("rep added");
			saveRole(session, rowId, roleName);
	
		}
		
		else if(delete.equals("deleteCompany")) {
			System.out.println("delete company");
			
		
		String[] record = rowId.split("[|]");	
		System.out.println(record[0] + record[1] + record[2]);
		int companyId = Integer.parseInt(record[0]);
		int roleId = Integer.parseInt(record[1]);
		int repId = Integer.parseInt(record[2]);
		
		
		if (currentUser != null && currentUser.isAdmin()) {
			companyDAO.deleteCompany(companyId);
			System.out.println("company deleted");
		}
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	
		}
	
	@RequestMapping(path="/deleteCompanyPosition", method=RequestMethod.POST)
	public String deleteCompanyPosition(HttpSession session, @RequestParam String rowId) {		
		
		String[] record = rowId.split("[|]");
		System.out.println(record[0] + record[1] + record[2]);
		int companyId = Integer.parseInt(record[0]);
		int roleId = Integer.parseInt(record[1]);
		int repId = Integer.parseInt(record[2]);
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			System.out.println("role deleted");
			companyDAO.deleteCompanyPosition(roleId);
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	}
	
	@RequestMapping(path="/deleteCompanyRepresentative", method=RequestMethod.POST)
	public String deleteCompanyRepresentative(HttpSession session, @RequestParam String rowId) {		
		
		String[] record = rowId.split("[|]");
		int companyId = Integer.parseInt(record[0]);
		int roleId = Integer.parseInt(record[1]);
		int repId = Integer.parseInt(record[2]);
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			System.out.println("rep deleted");
			companyDAO.deleteCompanyRep(repId);
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	}
	
	@RequestMapping(path="/saveCompany", method=RequestMethod.POST)
	public String saveCompany(HttpSession session, @RequestParam String companyName) {		
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			System.out.println("company Saved");
			companyDAO.saveCompany(companyName);
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	}
	
	@RequestMapping(path="/saveRepresentative", method=RequestMethod.POST)
	public String saveRepresentative(HttpSession session, @RequestParam String rowId, @RequestParam String repName, @RequestParam String repEmail) {		
		
		String[] record = rowId.split("[|]");
		int companyId = Integer.parseInt(record[0]);
		int roleId = Integer.parseInt(record[1]);
		int repId = Integer.parseInt(record[2]);
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			System.out.println("company Saved");
			companyDAO.saveRep(companyId, repName, repEmail);
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	}
	
	@RequestMapping(path="/saveRole", method=RequestMethod.POST)
	public String saveRole(HttpSession session, @RequestParam String rowId, @RequestParam String roleName) {		
		String[] record = rowId.split("[|]");
		int companyId = Integer.parseInt(record[0]);
		int roleId = Integer.parseInt(record[1]);
		int repId = Integer.parseInt(record[2]);
		
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isAdmin()) {
			System.out.println("company Saved");
			companyDAO.saveRep(companyId, roleName);
		}
		return "redirect:/users/" + currentUser.getUserName() + "/matchmaking";
	}
	
	@RequestMapping("/users/{userName}/resources")
	public String showResourcesPage(@PathVariable String userName, HttpSession session){
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isStudent()) {
			return "students/resources";
		}
		if (currentUser != null && currentUser.isAdmin()) {
			return "admins/resources";
		}
		if (currentUser != null && currentUser.isGuest()) {
			return "redirect:/guest";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/users/{userName}/chat")
	public String showChatPage(@PathVariable String userName, HttpSession session){
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && currentUser.isStudent()) {
			return "chat";
		}
		if (currentUser != null && currentUser.isAdmin()) {
			return "chat";
		}
		if (currentUser != null && currentUser.isGuest()) {
			return "redirect:/guest";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/guest")
	public String displayGuestMessagePage(HttpSession session) {
		return "guests/home";
	}
	
	
}
