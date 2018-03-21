package com.revature.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOClass implements UserDAO{

	// new user registration  ***************FIX PROFILE PIC AND BIRTHDATE************************
	
	public boolean makeUser(int user_id, String first_name, String last_name, String username, String password, Blob profile_pic, String email, int age, Timestamp birthdate) {
		
		String hql = "INSERT INTO User(:id, :fname, :lname, :uname, :pswd, :pp, :email, :age, :bd)";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("id", user_id);
		query.setParameter("fname", first_name);
		query.setParameter("lname", last_name);
		query.setParameter("uname", username);
		query.setParameter("pswd", password);
		query.setParameter("pp", null);
		query.setParameter("email", email);
		query.setParameter("age", age);
		query.setParameter("bd", null);
		
		int result = query.executeUpdate();
		
		if(result == 0) {
			
			return false;
		}
		
		return true;
	}
		
		
	// search the db and login the user if account is found
		
	@SuppressWarnings("unused")
	public User login(String username, String password) {
			
		String hql = "from User"
				+ " WHERE USERNAME = :user AND PASS_WORD = :pswd";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("user", username);
		query.setParameter("pswd", password);
		
		List<User> list = query.list();
		
		if(list.isEmpty()) {
			session.close();
			return null;
		}
		
		User a = list.get(0);
		session.close();
		
		return a;
	}
	
	
	// user wants to update their personal details
	
	public void updateDetails() {
		
		
	}
	
	
	// user wants to reset their password
	
	public void resetPassword(String password) {
		
		
	}
	
	
	// user wants to put up a profile picture
	
	public boolean changePic() {
		
		return false;
	}
	
	
	// user wants to create a post
	
	public boolean createPost(String text) {
		
		return false;
	}
	
	
	// user wants to view their own profile
	
	public void viewMyProfile(User user) {
		
		
	}
	
	
	// user wants to checkout another user's profile
	
	public void viewAProfile(User user) {
		
		
	}
	
	
	// user wants to see everyones posts
	
	public void viewFeed() {
		
		
	}
	
	
	// user wants to like a post
	
	public boolean likePost(Post post) {
		
		return false;
	}
	
	
	// user wants to logout
	
	public boolean logout() {
		
		return false;
	}
}
