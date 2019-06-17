package com.techelevator.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Event;
import com.techelevator.model.EventDAO;
import com.techelevator.model.User;
import com.techelevator.model.User_EventDAO;

@Controller
public class EventController{
	
	@Autowired(required=false)
	public EventDAO eventDAO;
	
	@Autowired(required=false)
	public User_EventDAO userEventDAO;
	
	
	@RequestMapping(path="/deleteEvent", method=RequestMethod.POST)
	public String displayEvents(HttpSession session, @RequestParam(required=false) String delete,
								@RequestParam(required=false) Integer id, @RequestParam(required=false) String event_name, @RequestParam(required=false) String description,
								@RequestParam(required=false) String start_date, @RequestParam(required=false) String end_date, @RequestParam(required=false) String mandatory){
		User currentUser = (User) session.getAttribute("currentUser");
		 if(delete != null && id != null){
		 	eventDAO.deleteEvent(id);
		 }else if (delete != null && id == null) {
			 return "redirect:/users/" + currentUser.getUserName() + "/events"; 
		 }else if(id != null){
			
			 if(event_name.length() > 1) {
				eventDAO.updateEventName(id, event_name);
			}
			if(description.length() > 1) {
				eventDAO.updateEventDescription(id, description);
			}
			if(start_date.length() > 1) {
				Timestamp t = Timestamp.valueOf(start_date);
				eventDAO.updateEventStartD(id, t);
			}
			if(end_date.length() > 1) {
				Timestamp t2 = Timestamp.valueOf(end_date);
				eventDAO.updateEventEndD(id, t2);
			}
			if(mandatory.length() > 1) {
				if(mandatory.equalsIgnoreCase("true")) {
					eventDAO.updateEventMandatory(id, true);
				}else if(mandatory.equalsIgnoreCase("false")) {
					eventDAO.updateEventMandatory(id, false);
				}
			}
		}else {
			Event event = new Event();
			event.setEvent_name(event_name);
			event.setDescription(description);
			event.setStart_date(Timestamp.valueOf(start_date));
			event.setEnd_date(Timestamp.valueOf(end_date));
			if(mandatory.equalsIgnoreCase("true")) {
				event.setMandatory(true);
			}else if(mandatory.equalsIgnoreCase("false")) {
				event.setMandatory(false);
			}
			eventDAO.saveEvent(event);
			
		}
		 
		 
		return "redirect:/users/" + currentUser.getUserName() + "/events";
	}
	
	
	
	
	
	
	
}