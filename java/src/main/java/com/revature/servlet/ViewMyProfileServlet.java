package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.beans.Post;
import com.revature.beans.User;
import com.revature.dao.PostDAOClass;
import com.revature.dao.UserDAOClass;

/**
 * Servlet implementation class ViewMyProfileServlet
 */
public class ViewMyProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String id = request.getParameter("user_id");
		int user_id = Integer.parseInt(id);
		
		UserDAOClass user = new UserDAOClass();
		User a = user.viewMyProfile(username);
		
		request.setAttribute("first_name", a.getFirst_name());
		request.setAttribute("last_name", a.getLast_name());
		request.setAttribute("email", a.getEmail());
		request.setAttribute("birthdate", a.getBirthdate());
		
		Gson gson = new Gson();
		
		
		PostDAOClass post = new PostDAOClass();
		List<Post> posts = post.getPostsByUserID(user_id);
		
		request.setAttribute("listOfPosts", posts);
		response.sendRedirect("http://localhost:4200/userpage");		//*******************************
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
