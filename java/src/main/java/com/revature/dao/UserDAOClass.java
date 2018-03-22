package com.revature.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOClass implements UserDAO{

	// new user registration  ***************FIX PROFILE PIC AND BIRTHDATE************************
	
	@SuppressWarnings("deprecation")
	public boolean makeUser(int user_id, String first_name, String last_name, String username, String password, Blob profile_pic, String email, int age, Timestamp birthdate) {
		
		String hql = "INSERT INTO User VALUES(:id, :fname, :lname, :uname, :pswd, :pp, :email, :age, :bd)";
		
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
		session.close();
		
		if(result == 0) {
			
			System.out.println("Error. Was not able to register user.");
			return false;
		}
		
		System.out.println("Registered user successfully.");
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
			
			System.out.println("Error. User was not able to login.");
			session.close();
			return null;
		}
		
		User a = list.get(0);

		System.out.println("User is logged in.");
		session.close();
		
		return a;
	}
	
	
	// user wants to update their personal details
	
	public void updateDetails(int user_id, String first_name, String last_name, String username, Blob profile_pic, String email, int age, Timestamp birthdate) {
		
		String hql = "UPDATE User SET FIRST_NAME = :fname, LAST_NAME = :lname, USERNAME = :uname, PROFILE_PIC = :pp, EMAIL = :email, AGE = :age, BIRTHDATE = :bd WHERE USER_ID = :user_id";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("user_id", user_id);
		query.setParameter("fname", first_name);
		query.setParameter("lname", last_name);
		query.setParameter("uname", username);
		query.setParameter("pp", null);
		query.setParameter("email", email);
		query.setParameter("age", age);
		query.setParameter("bd", null);
		
		int result = query.executeUpdate();
		
		if(result == 0) {
			
			System.out.println("Update failed.");
			return;
		}else
			System.out.println("Update successful");
		session.close();
	}
	
	
	// user wants to reset their password			***********CALL EMAIL METHOD AND EMAIL ALERT TO USER*************
	
	public void resetPassword(String username, String password) {
		
		String hql = "UPDATE User SET PASS_WORD = :pswd WHERE USERNAME = :uname";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uname", username);
		query.setParameter("pswd", password);

		int result = query.executeUpdate();
		
		if(result == 0) {
			
			System.out.println("Error. Password has not been reset.");
			return;
		} else {
			
			System.out.println("Password has been reset");
			
			hql = "SELECT EMAIL FROM User WHERE USERNAME = :uname";

			session = HibernateUtil.getSession();
			Query query2 = session.createQuery(hql);

			query2.setParameter("uname", username);
			List<String> email_list = query2.list();
			String email = email_list.get(0);
			emailUser(email, password);
		}
		session.close();
	}
	
	
	// user must be emailed once they reset their password
	
	public void emailUser(String email, String password) {
		
		
	}
	
	
	// user wants to put up a profile picture
	
	public void changePic(int user_id, Blob new_pic) {
		
		String hql = "UPDATE User SET PROFILE_PIC = :np WHERE USER_ID = :id";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("np", new_pic);
		query.setParameter("id", user_id);

		int result = query.executeUpdate();

		if(result == 0) {
			
			System.out.println("Error. The pic has not been changed");
			return;
		}
		
		System.out.println("The pic has been changed");
	}
	
	
	// user wants to create a post
	
	public boolean createPost(int post_id, String hash, String user_text, Blob profile_pic, int user_id) {
		
		String hql = "INSERT INTO Post VALUES(:id, :hash, :text, :pic, :u_id)";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("id", post_id);
		query.setParameter("hash", hash);
		query.setParameter("text", user_text);
		query.setParameter("pic", profile_pic);
		query.setParameter("u_id", user_id);
		
		
		int result = query.executeUpdate();
		session.close();
		
		if(result == 0) {
			
			System.out.println("Error. Was not able to create post.");
			return false;
		}
		
		System.out.println("Post was created successfully.");
		return true;
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
