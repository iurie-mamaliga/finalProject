package com.techelevator.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;

public class Event{
	
	private Long event_id;
	private String event_name;
	private String description;
	private Timestamp start_date;
	private Timestamp end_date;
	private boolean mandatory;
	private String duration;
	private String startOf;

	
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	public String getDuration() {
		int hr = this.end_date.getHours() - this.start_date.getHours();
		int min = this.end_date.getMinutes() - this.start_date.getMinutes();
		String fin = "";
		if(min < 10) {
			 fin = hr + ":" + min + "0 hr.";
		}else {
		 fin = hr + ":" + min + " hr.";
		}
		return fin;
		
	}
	
	public String getStartOf() {
		int month = this.start_date.getMonth();
		int day = this.start_date.getDate();
		int hr = this.start_date.getHours();
		int min = this.start_date.getMinutes();
		String fin = "";
		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		if(min < 10) {
			fin = monthNames[month] + " " + day + " at " + hr + ":0" + min + ".";
		}else {
			fin = monthNames[month] + " " + day + " at " + hr + ":" + min + ".";
		}
		return fin;
	}
	
	
	
	@Override
	public String toString() {
		String formattedDate = new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(this.start_date);
		String formattedTime = new SimpleDateFormat("h:mm a").format(this.start_date);
		return "<em>" + this.event_name + "</em> &mdash; " + formattedDate + " at " + formattedTime;
	}
	

	
}