package com.techelevator.model;

import java.util.List;

public interface User_EventDAO {
	
	public List<Event> userEvents(int userId);  //provides all upcoming events for given user
	
	public void usersToEvent(int [] userIds, int eventId); // given an array of user Ids, adds users to events
	
	public List<User> usersInEvent(int eventId);  //provides a List of users enrolled in an event
	
}