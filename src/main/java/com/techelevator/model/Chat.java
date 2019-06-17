package com.techelevator.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Chat {
	
	private User user;
	private String userMessage;
	private Set<String> tokens;
	private String response;
	private String startOfResponse;
	
	private boolean mentionsSelf;
	private boolean mentionsEvent;
	private boolean mentionsMatchmaking;
	private boolean mentionsResources;
	

	public Chat(User user, String userMessage) {
		this.user = user;
		this.userMessage = userMessage;
		this.tokens = tokenize(userMessage);
		this.startOfResponse = prepareResponse();
	}

	private Set<String> tokenize(String str) {
		str = str.toLowerCase();
		str = str.replaceAll("[.|'|,|!|?|&]", "");
		Set<String> tokens = new HashSet<String>(Arrays.asList(str.split(" ")));
		tokens = removeStopWords(tokens);
		return tokens;
	}

	private String prepareResponse() {

		String out = "";


		this.mentionsSelf = mentionsSelf(tokens);
		this.mentionsEvent = mentionsEvent(tokens);
		this.mentionsMatchmaking = mentionsMatchmaking(tokens);
		this.mentionsResources = mentionsResources(tokens);

		if (mentionsSelf && mentionsMatchmaking) {
			out = "You'll be attending matchmaking with: ";
			
		} else if (!mentionsSelf && mentionsMatchmaking) {
			out = "These companies are coming to matchmaking: ";
			
		} else if (mentionsSelf && mentionsEvent) {
			out = "Your upcoming events are: ";
			
		} else if (!mentionsSelf && mentionsEvent) {
			out = "The next events are: ";
			
		} else if (mentionsSelf && mentionsResources) {
			out = "Your resources are: ";
			
		} else if (!mentionsSelf && mentionsResources) {
			out = "Here are some general resources: ";
			
		} else if (tokens.contains("joke")) {
			out = "Why do Java programmers wear glasses?<br /><br />...because they don't C#";
			
		} else if (tokens.contains("dance") || tokens.contains("sing") || tokens.contains("rickroll")) {
			out = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/oHg5SJYRHA0?autoplay=1&controls=0\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\"></iframe>";
		
		}

		else {

			if (tokens.contains("time") || tokens.contains("date") || tokens.contains("day")) {
				LocalDateTime currentTime = LocalDateTime.now();
				DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
				out = "The current time is " + currentTime.format(timeFormatter) + ". It's "
						+ currentTime.format(dateFormatter) + ".";
				
			} else {
				String searchStr = tokens.toString();
				searchStr = searchStr.replaceAll("[\\[|\\]]", "");
				searchStr = searchStr.replaceAll("[,]", "");
				searchStr = searchStr.replaceAll(" ", "+");
				out = "I'm stumped. Try <a href=\"https://duckduckgo.com/?q=" + searchStr
						+ "\">searching for the answer.</a>";
				
			}
		}
		
		return out;
		
	}

	private Set<String> removeStopWords(Set<String> tokens) {
		String[] stopWordsArr = { "a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an",
				"and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear",
				"did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "going", "got", "had",
				"has", "have", "he", "her", "hers", "him", "his", "how", "how's", "however", "if", "in",
				"into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "might", "most", "must",
				"neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own",
				"rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their",
				"them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was",
				"we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would",
				"yet", "you", "your" };
		Set<String> stopWords = new HashSet<String>(Arrays.asList(stopWordsArr));
		tokens.removeAll(stopWords);
		return tokens;
	}
	
	private boolean mentionsEvent(Set<String> tokens) {
		return tokens.contains("event") || tokens.contains("events");
	}

	private boolean mentionsSelf(Set<String> tokens) {
		return tokens.contains("my") || tokens.contains("mine") || tokens.contains("me") || tokens.contains("i");
	}

	private boolean mentionsMatchmaking(Set<String> tokens) {
		return tokens.contains("match") || tokens.contains("matchmaking") || tokens.contains("companies")
				|| tokens.contains("company") || tokens.contains("meeting") || tokens.contains("meetings")
				|| tokens.contains("interview") || tokens.contains("interviews");
	}

	private boolean mentionsResources(Set<String> tokens) {
		return tokens.contains("resource") || tokens.contains("paperwork") || tokens.contains("documents")
				|| tokens.contains("reference");
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	
	public User getUser() {
		return user;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public Set<String> getTokens() {
		return tokens;
	}

	public String getResponse() {
		return response;
	}

	public String getStartOfResponse() {
		return startOfResponse;
	}

	public boolean mentionsSelf() {
		return mentionsSelf;
	}

	public boolean mentionsEvent() {
		return mentionsEvent;
	}

	public boolean mentionsMatchmaking() {
		return mentionsMatchmaking;
	}

	public boolean mentionsResources() {
		return mentionsResources;
	}


}
