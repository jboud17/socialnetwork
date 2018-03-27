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
		List<Post> userPosts = postDao.getPostsOfLoggedInUser(session);
		List<Object> postLikes = postDao.getPostLikes();
		
		Gson gson = new Gson();
		String json = gson.toJson(userPosts);
		String split[] = json.split("}}");
		int noLikes = 0;
		String returnJSON = "";
		
		for(int i=0; i<userPosts.size(); i++) {
			Post p = userPosts.get(i);
			boolean hasLikes = false;
			for(int j=0; j<postLikes.size(); j++) {
				Object row = postLikes.get(j);
				Object[] r = (Object[]) row;
				Integer postId = (Integer) r[0];
				Integer totalLikes = ((Long) r[1]).intValue();
				if(p.getPost_id() == postId) {
					returnJSON += split[i] + "}," + "\"post_likes\":\"" + totalLikes + "\"}";
					hasLikes = true;
					break;
				} 
			}

			if(!hasLikes) {
				returnJSON += split[i] + "}," + "\"post_likes\":\"" + noLikes + "\"}";
			}
			
			if(userPosts.size()-i == 1) {
				returnJSON += "]";
			}
		}

		response.getWriter().write(returnJSON);
	 }
}
