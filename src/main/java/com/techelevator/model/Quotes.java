package com.techelevator.model;

public class Quotes {
	
	private Long id;
	private String category;
	private String quotes;
	private String quote_source;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuote() {
		return quotes;
	}
	public void setQuote(String quote) {
		this.quotes = quote;
	}
	public String getQuote_source() {
		return quote_source;
	}
	public void SetQuote_source(String author) {
		this.quote_source = author;
	}
	
	
	
}