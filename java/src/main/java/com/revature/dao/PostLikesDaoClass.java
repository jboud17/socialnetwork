package com.revature.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.PostLikes;
import com.revature.util.HibernateUtil;

public class PostLikesDaoClass implements PostLikesDao {

	@Override
	public boolean insertRecord(int postId, int userId) {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		PostLikes pl = new PostLikes(postId, userId);
		session.save(pl);
		
		tx.commit();
		session.close();
		
		return true;
	}

}
