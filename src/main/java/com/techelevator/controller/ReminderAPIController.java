package com.techelevator.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.model.Event;
import com.techelevator.model.EventDAO;
import com.techelevator.model.MatchmakingCompanyDAO;
import com.techelevator.model.QuotesDAO;
import com.techelevator.model.TextMessage;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.model.User_EventDAO;

@RestController
public class ReminderAPIController {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private EventDAO eventDao;
	
	@Autowired
	private User_EventDAO userEventDao;
		
	@SuppressWarnings("deprecation")
	@RequestMapping(path="/setReminder", method=RequestMethod.POST)
	public void setReminder(HttpSession session,
								@RequestParam String userName,
								@RequestParam int eventId) {
	
		User user = (User) userDao.getUserByUserName(userName);
		Event event = eventDao.getEvent(eventId);		
		Date eventDt = event.getStart_date();
		
		
		
		//queue reminder for day of event
		
		Date dayOfEvent = event.getStart_date();
		dayOfEvent.setHours(0);
			
		try {
			TextMessage txtDayOfEvent = new TextMessage(user.getPhoneNumber());
			txtDayOfEvent.sendEventReminder(event, dayOfEvent);
		} catch (Exception e) {}
		
		
		
		// queue reminder for day before event
		
		Date dayBeforeEvent = event.getStart_date();
		dayBeforeEvent.setDate(eventDt.getDate()-1);
		try {
			TextMessage txtDayBefore = new TextMessage(user.getPhoneNumber());
			txtDayBefore.sendEventReminder(event, dayBeforeEvent);
		} catch (Exception e) {}
		
		
	}
}
