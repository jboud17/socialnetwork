package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.beans.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOClass;
import com.revature.util.FrontController;

public class AllUsersServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FrontController.addHeader(response);
		
		response.setContentType("application/json");
		UserDAO userDao = new UserDAOClass();
		List<User> allUsers = userDao.allUsers();
		
		Gson gson = new Gson();
		String json = gson.toJson(allUsers);
		response.getWriter().write(json);
	 }
}
