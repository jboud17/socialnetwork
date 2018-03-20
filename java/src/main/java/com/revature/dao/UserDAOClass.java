package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOClass implements UserDAO{

	// new user registration
	
	public boolean makeUser() {
		
		return false;
	}
		
		
	// search the db and login the user if account is found
		
	public boolean login(String username, String password) {
			
		User user = null;
		String sql = "SELECT *" 
				+ " FROM USERS"
				+ " WHERE USERNAME = ? AND PASS_WORD = ?";
		try{

			ResultSet rs = null;
			user = constructUser(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	private User constructUser(ResultSet rs) throws SQLException{
		
		if(rs.next()){
			int id = rs.getInt("USERS_ID");
			String username = rs.getString("USERNAME");
			String lastName = rs.getString("LAST_NAME");
			String firstName = rs.getString("FIRST_NAME");
			String password = rs.getString("PASSWORD");
			int age = rs.getInt("AGE");
			String email = rs.getString("EMAIL");
			return new User();
		}
		return null;
	}
	
	
	// user wants to update their personal details
	
	public void updateDetails() {
		
		
	}
	
	
	// user wants to reset their password
	
	public void resetPassword() {
		
		
	}
	
	
	// user wants to put up a profile picture
	
	public boolean changePic() {
		
		return false;
	}
	
	
	// user wants to create a post
	
	public boolean createPost() {
		
		return false;
	}
	
	
	// user wants to view their own profile
	
	public void viewMyProfile() {
		
		
	}
	
	
	// user wants to checkout another user's profile
	
	public void viewAProfile() {
		
		
	}
	
	
	// user wants to see everyones posts
	
	public void viewFeed() {
		
		
	}
	
	
	// user wants to like a post
	
	public boolean likePost() {
		
		return false;
	}
	
	
	// user wants to logout
	
	public boolean logout() {
		
		return false;
	}
}
