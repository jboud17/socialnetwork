package com.revature.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.PostLikes;
import com.revature.util.HibernateUtil;


public class PostLikesDaoClass implements PostLikesDao {

	private static final Logger logger = LogManager.getLogger(PostDAOClass.class);
	
	@Override
	public boolean insertRecord(int postId, int userId) {
				
		Session session = HibernateUtil.getSession();
		String hql = "FROM PostLikes WHERE post_id = :pId AND user_id = :uId";
		Query q = session.createQuery(hql);
		
		q.setParameter("pId", postId);
		q.setParameter("uId", userId);
		List list = q.list();
		
		if(!list.isEmpty()) {
			session.close();
			logger.assertLog(true, "Error. The post could not be recorded.");
			return false;
		}
		
		Transaction tx = session.beginTransaction();
		PostLikes pl = new PostLikes(postId, userId);
		session.save(pl);
		tx.commit();
		
		session.close();
		logger.assertLog(true, "A post with id "+postId+" recieved a like from "+userId+" , and it has been recorded in the table.");

		
		return true;
	}

}
