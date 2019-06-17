package com.techelevator.controller;

import java.util.List;
import java.util.Objects;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Chat;
import com.techelevator.model.Event;
import com.techelevator.model.EventDAO;
import com.techelevator.model.MatchmakingCompany;
import com.techelevator.model.MatchmakingCompanyDAO;
import com.techelevator.model.QuotesDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.model.User_EventDAO;
import com.techelevator.security.AuthorizationFilter;

@RestController
public class ChatAPIController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private EventDAO eventDao;
	
	@Autowired
	private User_EventDAO userEventDao;
	
	@Autowired
	private MatchmakingCompanyDAO mcDao;
	
	@Autowired
	private QuotesDAO quotesDao;
	
	@RequestMapping(path="/chat/sendUserMessage", method=RequestMethod.GET)
	public String sendUserMessage(HttpSession session,
								@RequestParam String userName,
								@RequestParam String userMessage) {
	
		String out = null;
		User currentUser = (User) session.getAttribute("currentUser");
		if (userName.equals(currentUser.getUserName())) {
			Chat chat = new Chat(currentUser, userMessage);
			out = chat.getStartOfResponse();
			long userId = currentUser.getId();
			
			if (!chat.mentionsSelf() && chat.mentionsEvent()) {
				out += listToString(eventDao.getNext7Events());
				
			} else if (chat.mentionsSelf() && chat.mentionsEvent()) {
				out += listToString(userEventDao.userEvents((int) userId));
				
			} else if (!chat.mentionsSelf() && chat.mentionsMatchmaking()) {
				out += listToString(mcDao.showAllCompanies());
				
			} 
			chat.setResponse(out);
		}
			return out;
	}
	
	private String listToString(List<?> list) {
		String out = "";
		int i = 0;
		for (Object o : list) {
			if (i == 0) {
				out += "<ul>";
			}
			out += "<li>" + o.toString() + "</li>";
			if (i == list.size() - 1) {
				out += "</ul>";
			}
			i++;
		}
		return out;
	}
	
	
}
