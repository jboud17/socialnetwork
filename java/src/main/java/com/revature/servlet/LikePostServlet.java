package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.PostLikesDaoClass;
import com.revature.util.FrontController;

public class LikePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FrontController.addHeader(resp);
		resp.setContentType("application/json");
		
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//      resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//      resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//      resp.addHeader("Access-Control-Max-Age", "1728000");
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		int postId = Integer.parseInt(req.getParameter("postId"));
		
		PostLikesDaoClass pldc = new PostLikesDaoClass();
		
		resp.setContentType("text/html");
		
		if(pldc.insertRecord(postId, userId)) {
			resp.getWriter().write("[{\"status\" : \"green\" }]");
			System.out.println("New record inserted to post_like table");
		} else {
			resp.getWriter().write("[{\"status\" : \"red\" }]");
			System.out.println("Record already exists in post_like table");
		}
		
		
		
	}
}
