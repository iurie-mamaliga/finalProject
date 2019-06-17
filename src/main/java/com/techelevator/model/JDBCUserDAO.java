package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;
import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public void saveUser(String firstName, String lastName, String userName, String phoneNumber, String password, String email) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		// generate 64-character string for reset password key
		byte[] resetKeyByte = hashMaster.generateRandomSalt();
		String resetKey = new String(Base64.encode(resetKeyByte)).substring(0,64);
		String defaultRole = "guest";
		
		String sqlSaveUser = "INSERT INTO app_user(first_name, last_name, user_name, role, password, salt, reset_key, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";	
		jdbcTemplate.update(sqlSaveUser,
				firstName, lastName, userName, defaultRole, hashedPassword, saltString, resetKey, phoneNumber, email);
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isResetPasswordKeyValid(String userName, String resetKey) {
		String sqlCheckKey = "SELECT * "+
			      			"FROM app_user "+
			      			"WHERE UPPER(user_name) = ? ";
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlCheckKey, userName.toUpperCase());
		String keyInDb = "";
		if(user.next()) {
			keyInDb = user.getString("reset_key");
		}
		if (!keyInDb.equals(resetKey)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public String getPasswordResetKey(String userName) {
		String sqlGetKey = "SELECT reset_key "+
							"FROM app_user "+
							"WHERE UPPER(user_name) = ?";
		SqlRowSet keyRowSet = jdbcTemplate.queryForRowSet(sqlGetKey, userName.toUpperCase());
		String key = "";
		if (keyRowSet.next()) {
			key = keyRowSet.getString("reset_key");
		}
		
		return key;
	}


	@Override
	public void updatePassword(String userName, String password) {
		
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		// update reset key when password is changed as well
				
		String resetKey = RandomStringUtils.randomAlphanumeric(64);
		jdbcTemplate.update("UPDATE app_user SET password = ?, salt = ?, reset_key = ? WHERE UPPER(user_name) = ?", hashedPassword, saltString, resetKey, userName.toUpperCase());
		
	}

	@Override
	public Object getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setId(user.getLong("id"));
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setRole(user.getString("role"));
			thisUser.setPhoneNumber(user.getString("phone"));
		}

		return thisUser;
	}

	@Override
	public void updateUserRole(String userName, String role) {
		if (role.equals("admin") || role.equals("guest") || role.equals("student")) {
			jdbcTemplate.update("UPDATE app_user SET role = ? WHERE user_name = ?", role, userName);
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> allUsers = new ArrayList<>();
		
		String sqlGetUsers ="SELECT * "+
		"FROM app_user "+
		"ORDER BY user_name ASC "+
		"LIMIT 500";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlGetUsers); 
		User thisUser = null;
		while(user.next()) {
			thisUser = new User();
			thisUser.setId(user.getLong("id"));
			thisUser.setFirstName(user.getString("first_name"));
			thisUser.setLastName(user.getString("last_name"));
			thisUser.setUserName(user.getString("user_name"));
			thisUser.setEmail(user.getString("email"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setRole(user.getString("role"));
			thisUser.setPhoneNumber(user.getString("phone"));
			allUsers.add(thisUser);
		}

		return allUsers;
	}

	@Override
	public void deleteUser(String userName) {
		User user = (User) getUserByUserName(userName);
		long userId = user.getId();
		jdbcTemplate.update("DELETE FROM user_matchmaking WHERE user_id = ?", userId);
		jdbcTemplate.update("DELETE FROM user_event WHERE user_id = ?", userId);
		jdbcTemplate.update("DELETE FROM app_user WHERE user_name = ?", userName);
	}
	
	@Override
	public String getUserNameByEmail(String email) {
		String sqlUserName = "SELECT * "+
				"FROM app_user "+
				"WHERE UPPER(email) = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlUserName, email.toUpperCase());
		String item = "";
		if (rowSet.next()) {
			item = rowSet.getString("user_name");
		}
		return item;
		
	}
	

}
