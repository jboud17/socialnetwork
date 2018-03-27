package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.PostLikes;
import com.revature.util.HibernateUtil;


public class PostLikesDaoClass implements PostLikesDao {

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
			return false;
		}
		
		Transaction tx = session.beginTransaction();
		PostLikes pl = new PostLikes(postId, userId);
		session.save(pl);
		tx.commit();
		session.close();
		return true;
	}

}
