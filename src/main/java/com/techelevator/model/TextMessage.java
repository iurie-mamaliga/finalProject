package com.techelevator.model;
//package com.textmagic.sdk;

import com.textmagic.sdk.ClientException;
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class TextMessage {
	private TMNewMessage m;
	private RestClient client;
	
	public TextMessage(String phoneNum) {
		phoneNum = phoneNum.replaceAll("-", "");
		
		//prefix number with 1 if needed
		if (phoneNum.substring(0, 1) != "1") {
			phoneNum = "1" + phoneNum;
		}
		this.client = new RestClient("chrisrudzki", "QsyIChX8GxImNwOOAG61jiL9s7vnL8");
		this.m = client.getResource(TMNewMessage.class);
		this.m.setPhones(Arrays.asList(new String[] { phoneNum }));
	}
	
	@SuppressWarnings("deprecation")
	public void sendEventReminder(Event event, Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat("hh:mm 'on' MM-dd-yyyy");
		String dateString = format.format(date);
		
		String message = "Reminder: " + event.getEvent_name() + " starts at " + dateString;
		this.m.setText(message);		
		this.m.setSendingTime(date);
		
		try {
			this.m.send();
		} catch (final RestException e) {
			System.out.println(e.getErrors());
			throw new RuntimeException(e);
		}
		
	}
}