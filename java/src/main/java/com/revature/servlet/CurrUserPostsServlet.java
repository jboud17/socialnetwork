package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.beans.Post;
import com.revature.dao.PostDAO;
import com.revature.dao.PostDAOClass;
import com.revature.util.FrontController;

public class CurrUserPostsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FrontController.addHeader(response);
		HttpSession session = request.getSession(false);
		
		response.setContentType("application/json");
		PostDAO postDao = new PostDAOClass();
		List<Post> allPostsByUser = postDao.getPostsOfLoggedInUser(session);
		
		Gson gson = new Gson();
		String json = gson.toJson(allPostsByUser);
		response.getWriter().write(json);
	 }
}
