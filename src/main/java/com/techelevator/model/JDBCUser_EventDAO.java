package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCUser_EventDAO implements User_EventDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCUser_EventDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Event> userEvents(int userId) {
		List<Event> userEvents = new ArrayList<Event>();
		String sqlSelectResults = "SELECT event.event_id, event.event_name, event.description, event.start_date, event.end_date, event.mandatory " + 
				"FROM user_event " + 
				"JOIN event ON user_event.event_id = event.event_id " + 
				"WHERE user_event.user_id = ? AND event.start_date > NOW();";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults, userId);
		while(results.next()) {
			Event current = mapRowToEvent(results);
			userEvents.add(current);
		}
		return userEvents;
	}

	@Override
	public void usersToEvent(int[] userIds, int eventId) {
		for(int i = 0; i < userIds.length; i++) {
			int userId = userIds[i];
			userToEvent(userId, eventId);
		}
	}

	@Override
	public List<User> usersInEvent(int eventId) {
		List<User> usersInEvent = new ArrayList<User>();
		String sqlSelectResults = "SELECT app_user.id, app_user.user_name " + 
								"FROM app_user " + 
								"JOIN user_event ON app_user.id = user_event.user_id " + 
								"WHERE user_event.event_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectResults, eventId);
		while(results.next()) {
			User current = mapRowToUser(results);
			usersInEvent.add(current);
		}
		return usersInEvent;
	}
	
	
	public void userToEvent(int userId, int eventId) {
		String sqlInsertUsers = "INSERT INTO user_event(user_id, event_id) " + 
				"VALUES(?, ?);";
		jdbcTemplate.update(sqlInsertUsers, userId, eventId);
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
	
	private User mapRowToUser(SqlRowSet results) {
		User user = new User();
		user.setUserName(results.getString("user_name"));
		user.setId(results.getLong("id"));
		
		return user;
	}
	
}