package com.techelevator;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCUserDAO;
import com.techelevator.model.User;
import com.techelevator.security.PasswordHasher;


public class JdbcUserDaoIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JDBCUserDAO dao;
	private PasswordHasher hashMaster;
	 private JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/capstone");
		dataSource.setUsername("capstone_appuser");
		dataSource.setPassword("capstone_appuser1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		dao = new JDBCUserDAO(dataSource, hashMaster);
		
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void save_user_test() {
		User newUser = User("George", "General", "newuser", "8148248888", "passwordistureAs1", "george@mail.com");
		String firstName = "George";
		String lastName = "General";
		String userName = "newuser";
		String phoneNumber = "8148248888";
		String email = "george@mail.com";
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash("passwordistrueAs1", salt);
		String saltString = new String(Base64.encode(salt));
		
		// generate 64-character string for reset password key
		byte[] resetKeyByte = hashMaster.generateRandomSalt();
		String resetKey = new String(Base64.encode(resetKeyByte)).substring(0,64);
		String defaultRole = "guest";
		
		String sqlSaveUser = "INSERT INTO app_user(first_name, last_name, user_name, role, password, salt, reset_key, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";	
		jdbcTemplate.update(sqlSaveUser,
				firstName, lastName, userName, defaultRole, hashedPassword, saltString, resetKey, phoneNumber, email);
		
		Assert.assertEquals("George", newUser.getFirstName());
		Assert.assertEquals("General", newUser.getLastName());
		Assert.assertEquals("newuser", newUser.getUserName());
		Assert.assertEquals("guest",  newUser.getRole());
		Assert.assertEquals("8148248888", newUser.getPhoneNumber());
		Assert.assertEquals("george@mail.com",  newUser.getEmail());
	}

	private User User(String firstName, String lastName, String userName, String phoneNumber, String password, String email) {
		return null;
	}


	
	
}
