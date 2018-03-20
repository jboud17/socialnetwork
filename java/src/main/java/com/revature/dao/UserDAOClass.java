package com.revature.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOClass implements UserDAO{

	// new user registration
	
	public boolean makeUser() {
		
		return false;
	}
		
		
	// search the db and login the user if account is found
		
	@SuppressWarnings("unused")
	public User login(String username, String password) {
			
		String hql = " FROM USERS"
				+ " WHERE USERNAME = :user AND PASS_WORD = :pswd";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("user", username);
		query.setParameter("pswd", password);
		
		List<User> list = query.list();
		
		User a = list.get(0);
		session.close();
		
		if(list == null) {
			
			return null;
		}
		
		return a;
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
