package com.revature.dao;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Post;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDAOClass implements UserDAO{

	// new user registration  ***************FIX PROFILE PIC AND BIRTHDATE************************
	
	@SuppressWarnings("deprecation")
	public boolean makeUser(int user_id, String first_name, String last_name, String username, String password, String email, Timestamp birthdate) {
		
		String hql = "INSERT INTO User VALUES(:id, :fname, :lname, :uname, :pswd, :email, :bd)";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("id", user_id);
		query.setParameter("fname", first_name);
		query.setParameter("lname", last_name);
		query.setParameter("uname", username);
		query.setParameter("pswd", password);
		query.setParameter("email", email);
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
				+ " WHERE USERNAME = :user AND PASSWORD = :pswd";
		
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
	
	public void updateDetails(int userID, String first_name, String last_name, String email) {
		String hql = "UPDATE User SET FIRST_NAME = :fname, LAST_NAME = :lname, EMAIL = :email WHERE USER_ID = :uid";
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(hql);

		query.setParameter("fname", first_name);
		query.setParameter("lname", last_name);
		query.setParameter("email", email);
		query.setParameter("uid", userID);
		
		System.out.println("uid = "+userID);
		System.out.println("fname = "+first_name);
		System.out.println("lname = "+last_name);
		System.out.println("email = "+email);
		
		int result = query.executeUpdate();
		tx.commit();
		
		if(result == 0) {
			System.out.println("Update failed.");
			session.close();
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
	
	public void emailUser(final String email, final String password) {
		 //test1 works with SSL encryption!
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email,password);
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("to@no-spam.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		  //test2
		  //final String host = "localhost";
	      //try {
	
	    	  //Create session
	 
//	          Properties props = System.getProperties();
//	          props.put("mail.smtp.user", email);
//	          props.put("mail.smtp.password", password);
//	          props.put("mail.smtp.auth", "true");
//	          props.put("mail.smtp.ssl.enable", "true");
//	          props.put("mail.smtp.com" , "smtp.gmail.com");
//	          props.put("mail.transport.protocol", "smtp");
//	          props.put("mail.smtp.host", "smtp.gmail.com"); // smtp.gmail.com?
//	          props.put("mail.smtp.port", "587");
//
//
//	          javax.mail.Authenticator auth = null;
//	          auth = new javax.mail.Authenticator() {
//	              @Override
//	              public javax.mail.PasswordAuthentication getPasswordAuthentication() {
//	                  return new javax.mail.PasswordAuthentication(email, password);
//	              }
//	          };
//	          Session session = Session.getDefaultInstance(props, auth);
//	          //get transport object from session and connect to mail server
//	          Transport tr = session.getTransport("smtp");
//	          tr.connect(session.getProperty("mail.smtp.host"),  session.getProperty("mail.smtp.user"), session.getProperty("mail.smtp.password"));
//
//	          //create message  and set from,recipients,content and other stuff here on the message object.
//	          Message msg = new MimeMessage(session);
//	         //..................
//	         //...................
//
//	         //Save and send the message
//	          msg.saveChanges();
//	          tr.sendMessage(msg, msg.getAllRecipients());
//	          tr.close();
//	      }
//	      catch(Exception e)
//	      {
//	    	  e.printStackTrace();
//	      }
	}
	      
	   
	

	
	
	
	// user wants to put up a profile picture
	
	public void changePic(int user_id, Blob new_pic) {
		
/*		String hql = "UPDATE User SET PROFILE_PIC = :np WHERE USER_ID = :id";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("np", new_pic);
		query.setParameter("id", user_id);

		int result = query.executeUpdate();

		if(result == 0) {
			
			System.out.println("Error. The pic has not been changed");
			return;
		}
		
		System.out.println("The pic has been changed");*/
	}
		
	
	// user wants to view their own profile
	
	public User viewMyProfile(String username) {
		String hql = "FROM User WHERE USERNAME = :uname";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("uname", username);
		List<User> list = query.list();
			
		if(list.isEmpty()) {
			System.out.println("Error. User was not able to view their profile.");
			session.close();
			return null;
		}
			
		User a = list.get(0);
		
		System.out.println("User is logged in.");
		session.close();
		
		return a;
		
	}
	
	
	// user wants to checkout another user's profile
	
	public User viewAProfile(String fname, String lname) {
		String hql = "FROM User WHERE FIRST_NAME = :fname AND LAST_NAME = :lname";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("fname", fname);
		query.setParameter("lname", lname);
			
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
	
	
	// user wants to see everyones posts
	
	public List<Post> viewFeed() {
		
		String hql = "SELECT POST_TEXT FROM Post";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
				
		List<Post> list = query.list();
			
		if(list.isEmpty()) {
			
			System.out.println("Error. Posts could not assemble.");
			session.close();
			return null;
		}
			
		System.out.println("Posts have been gathered together!!");
		session.close();
		return list;
	}
	
	
	// user wants to like a post
	
	public boolean likePost(Post post) {
		
		return false;
	}
}
