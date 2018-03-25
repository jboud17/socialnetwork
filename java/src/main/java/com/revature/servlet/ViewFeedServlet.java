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

import com.revature.beans.Post;
import com.revature.dao.PostDAOClass;
import com.revature.dao.UserDAOClass;

/**
 * Servlet implementation class ViewFeedServlet
 */
public class ViewFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewFeedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAOClass a = new UserDAOClass();
		List<Post> posts = a.viewFeed();
		int i = 0;
		
		JSONArray user_info = new JSONArray();
		JSONObject obj2 = new JSONObject();
		
		while(i < posts.size()) {
			
			try {
				obj2.append("text", posts.get(i).getPost_text());
				obj2.append("user", posts.get(i).getUser().getUser_id());
				obj2.append("hash", posts.get(i).getHash());
		
				user_info.put(obj2);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		
//		response.setContentType("application/json");
		response.getWriter().print(user_info.toString());
		response.sendRedirect("http://localhost:4200/newsfeed");			//**********************************
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
