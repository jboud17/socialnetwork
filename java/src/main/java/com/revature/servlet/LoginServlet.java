package com.revature.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDAOClass;


public class LoginServlet extends HttpServlet{

	 private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		 response.sendRedirect("Login.html");		//	*******************************
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 UserDAOClass a = new UserDAOClass();
		 
		 System.out.println("This is the username: " + username + "\nand this is the password: " + password);
		 String page = "Login.html";	//	***********************************
		 
		 if(username != null && password != null) {
			 
			 boolean flag = a.login(username, password);
			 
			 if(flag) {
		   
				 System.out.println("You have successfully logged in.");
				 request.setAttribute("username", username);
				 page = "http://localhost:4200/home";			//	**************************************
			 } 
		 }
		 
		 	request.getRequestDispatcher(page).include(request, response);
	 	}
}
