package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.revature.dao.PostLikesDaoClass;
import com.revature.util.HibernateUtil;

public class LikePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		int postId = Integer.parseInt(req.getParameter("postId"));
		
		PostLikesDaoClass pldc = new PostLikesDaoClass();
		
		if(pldc.insertRecord(userId, postId)) {
			//success
		} else {
			//fail
		}
		
	}
}
