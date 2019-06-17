package com.techelevator.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JDBCEventDAO implements EventDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCEventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveEvent(Event event) {
		Long id = getNextId();
		String sqlInsertEvent = "INSERT INTO event (event_id, event_name, description, start_date, end_date, mandatory) " +
								"VALUES(?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sqlInsertEvent, id, event.getEvent_name(), event.getDescription(), event.getStart_date(), event.getEnd_date(), event.isMandatory());
		event.setEvent_id(id);
	}

	@Override
	public List<Event> getNext7Events() {
		List<Event> next7Events = new ArrayList<Event>();
		String sqlSelectResults = "SELECT * " +
								"FROM event " +
								"WHERE start_date > NOW() " +
								"ORDER BY start_date ASC " +
								"LIMIT 7;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults);
		while(results.next()) {
			Event current = mapRowToEvent(results);
			next7Events.add(current);
			
		}
		return next7Events;
	}

	@Override
	public List<Event> getAllEvents() {
		List<Event> allEvents = new ArrayList<Event>();
		String sqlSelectResults = "SELECT * " +
								"FROM event " +
								"ORDER BY start_date ASC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults);
		while(results.next()) {
			Event current = mapRowToEvent(results);
			allEvents.add(current);
		}
		return allEvents;
	}
	

	
	@Override
	public void updateEventName(int id, String event_name) {
		String sqlInsertEvent = "UPDATE event " + 
								"SET event_name = ? " + 
								"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, event_name, id);
		
	}

	@Override
	public void updateEventDescription(int id, String description) {
		String sqlInsertEvent = "UPDATE event " + 
				"SET description = ? " + 
				"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, description, id);
		
	}

	@Override
	public void updateEventStartD(int id, Timestamp start_date) {
		String sqlInsertEvent = "UPDATE event " + 
				"SET start_date = ? " + 
				"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, start_date, id);
		
	}

	@Override
	public void updateEventEndD(int id, Timestamp end_date) {
		String sqlInsertEvent = "UPDATE event " + 
				"SET end_date = ? " + 
				"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, end_date, id);
		
	}

	@Override
	public void updateEventMandatory(int id, boolean mandatory) {
		String sqlInsertEvent = "UPDATE event " + 
				"SET mandatory = ? " + 
				"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, mandatory, id);
		
	}
	
	@Override
	public void deleteEvent(int id) {
		String sqlInsertEvent = "DELETE FROM event " +  
								"WHERE event_id = ?;";
		jdbcTemplate.update(sqlInsertEvent, id);
	}
	
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('event_event_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	public Event getEvent(int id) {
		String sqlGetEvent = "SELECT * FROM event WHERE event_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetEvent, id);
		Event e = null;
		if (result.next()) {
			e = mapRowToEvent(result);
		}
		return e;	
	}
	
	private Event mapRowToEvent(SqlRowSet results) {
		Event event = new Event();
		event.setEvent_id(results.getLong("event_id"));
		event.setEvent_name(results.getString("event_name"));
		event.setDescription(results.getString("description"));
		event.setStart_date(results.getTimestamp("start_date"));
		event.setEnd_date(results.getTimestamp("end_date"));
		event.setMandatory(results.getBoolean("mandatory"));
		return event;
	}

	
	
	
}