package com.revature.dao;

/**
 * 
 * Post related methods are implemented here.
 * 
 * Group 2 - Trevor Fortner, Josh Bordeau, Pooja Suresh, Sonam Sherpa, JR
 * 
 */
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.revature.beans.Post;
import com.revature.util.HibernateUtil;

public class PostDAOClass implements PostDAO{

	private static final Logger logger = LogManager.getLogger(PostDAOClass.class);
	
	// get all users posts
	
	public List<Post> getAllPosts() {
		String hql = "FROM Post ORDER BY post_id DESC";
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		List<Post> list = query.list();
        
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
				+ " WHERE USER_ID = :uid ORDER BY post_id DESC";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uid", userId);
		
		List<Post> listToReturn = query.list();

		session.close();
		
		return listToReturn;
	}
	
	public List<Post> getPostsOfLoggedInUser(HttpSession httpSession) {
		String hql = "from Post"
				+ " WHERE USER_ID = :uid ORDER BY post_id DESC";
		
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
	
	public List<Object> getPostLikes() { 
		//create hql statement
		String hql = "SELECT post_id, COUNT(*) as likes FROM PostLikes GROUP BY post_id";
		
		//session and query
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		session.close();
		
		//return the number of likes for the post
		return list;
	}
}
