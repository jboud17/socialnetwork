package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.revature.beans.Post;
import com.revature.beans.User;
import com.revature.dao.PostDAOClass;
import com.revature.dao.UserDAOClass;

/**
 * Servlet implementation class ViewAProfileServlet
 */
public class ViewAProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		
		UserDAOClass user = new UserDAOClass();
		User a = user.viewAProfile(fname, lname);
		
		request.setAttribute("first_name", a.getFirst_name());
		request.setAttribute("last_name", a.getLast_name());
		request.setAttribute("email", a.getEmail());
		request.setAttribute("birthdate", a.getBirthdate());
		
		JSONArray user_info = new JSONArray();
		JSONObject obj2 = new JSONObject();

		try {
			
			obj2.append("first_name", a.getFirst_name());
			obj2.append("last_name", a.getLast_name());
			obj2.append("email", a.getEmail());
			obj2.append("birthdate", a.getBirthdate());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user_info.put(obj2);

		PostDAOClass post = new PostDAOClass();
		List<Post> posts = post.getPostsByUserID(a.getUser_id());
		
		request.setAttribute("listOfPosts", posts);
		
		try {
			obj2.append("listOfPosts", posts);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user_info.put(obj2);
		response.getWriter().print(user_info.toString());		
		response.sendRedirect("http://localhost:4200/someonespage");		//**********************************
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
