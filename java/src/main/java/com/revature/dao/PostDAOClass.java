package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.util.HibernateUtil;

public class PostDAOClass {

	// get all users posts
	
	public List<Post> getAllPosts() {
		String hql = "from Post";
		
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
				+ " WHERE USER_ID = :uid";
		
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery(hql);

		query.setParameter("uid", userId);
		
		return query.list();
	}
}
