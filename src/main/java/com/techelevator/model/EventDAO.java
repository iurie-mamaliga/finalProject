package com.techelevator.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface EventDAO {
	
	
	public void saveEvent(Event event);
	public List<Event> getNext7Events();
	public List<Event>getAllEvents();
	public void updateEventName(int id, String eventName);
	public void updateEventDescription(int id, String description);
	public void updateEventStartD(int id, Timestamp startDate);
	public void updateEventEndD(int id, Timestamp endDate);
	public void updateEventMandatory(int id, boolean mandatory);
	public void deleteEvent(int id);
	public Event getEvent(int id);
	
}