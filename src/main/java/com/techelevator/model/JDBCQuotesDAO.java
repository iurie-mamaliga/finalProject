package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCQuotesDAO implements QuotesDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCQuotesDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Quotes getQuote() {
		List<Quotes> quotes = new ArrayList<Quotes>();
		String sqlSelectResults = "SELECT * " +
								"FROM quotes;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults);
		while(results.next()) {
			Quotes current  = mapRowToQuote(results);
			quotes.add(current);
		}
		int max = quotes.size() -1;
		int x = (int)(Math.random()*((max - 0)+1))+0;
		Quotes q = quotes.get(x);
		return q;
	}

	private Quotes mapRowToQuote(SqlRowSet results) {
		Quotes quote = new Quotes();
		quote.setId(results.getLong("id"));
		quote.setCategory(results.getString("category"));
		quote.setQuote(results.getString("quotes"));
		quote.SetQuote_source(results.getString("quote_source"));
		return quote;
	
	}
	
}