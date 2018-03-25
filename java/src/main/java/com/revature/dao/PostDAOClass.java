package com.revature.dao;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.util.HibernateUtil;

public class PostDAOClass implements PostDAO{

	// get all users posts
	
	public List<Object> getAllPosts() {
		String hql = "SELECT p.post_id, p.title, p.hash, p.post_text, u.first_name, u.last_name, u.username "
				   + "FROM Post p LEFT JOIN p.user u";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

        List list = query.list();
        
		if(list.isEmpty()) {
			session.close();
			return null;
		}
		
		session.close();
		
		return list;
	}
	
	
	// get the user posts
	
	public List<Post> getPostsByUserID(int userId) {
		String hql = "from Post"
				+ " WHERE USER_ID = :uid";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uid", userId);
		
		List<Post> listToReturn = query.list();

		session.close();
		
		return listToReturn;
	}
	
	public List<Post> getPostsOfLoggedInUser(HttpSession httpSession) {
		String hql = "from Post"
				+ " WHERE USER_ID = :uid";
		
		int userID = (Integer) httpSession.getAttribute("uid");
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uid", userID);
		
		List<Post> listToReturn = query.list();

		session.close();
		
		return listToReturn;
	}
	
	// user wants to create a post
	
	public boolean createPost(int post_id, String img_hash, String user_text, String title, int user_id) {
		
		String hql = "INSERT INTO Post VALUES(:id, :hash, :text, :ttl :u_id)";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		query.setParameter("id", post_id);
		query.setParameter("hash", img_hash);
		query.setParameter("text", user_text);
		query.setParameter("ttl", title);
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
	
	public int postLikes() { 
		//this should probably take in an int for post id to count how many likes a post has
		
		String hql = "SELECT COUNT(user_id) FROM PostLikes p WHERE p.user_id = :id";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		
		int result = query.executeUpdate();
		session.close();
		
		return result;
	}

}
