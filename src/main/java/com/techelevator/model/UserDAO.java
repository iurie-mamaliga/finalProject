package com.techelevator.model;

import java.util.List;

public interface UserDAO {

	public void saveUser(String firstName, String lastName, String userName, String password, String phoneNumber, String email);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public Object getUserByUserName(String userName);
	
	public String getUserNameByEmail(String email);
	
	public List<User> getAllUsers();
	
	public void updateUserRole(String userName, String role);
	
	public void deleteUser(String userName);
	
	public boolean isResetPasswordKeyValid(String userName, String resetKey);

	String getPasswordResetKey(String userName);
		
}
