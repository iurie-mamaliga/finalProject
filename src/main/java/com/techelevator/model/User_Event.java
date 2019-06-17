package com.techelevator.model;

public class User_Event {
	
	private int event_id;
	private int user_id;  //is the userId, its named "id" in the User database
	
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}