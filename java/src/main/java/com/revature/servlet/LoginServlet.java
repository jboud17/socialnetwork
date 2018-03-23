package com.revature.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;
import com.revature.dao.UserDAOClass;


public class LoginServlet extends HttpServlet{

	 private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //do nothing, we shouldn't be calling this get
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
			
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 UserDAOClass a = new UserDAOClass();
		 
		 System.out.println("This is the username: " + username + "\nand this is the password: " + password);
		 String page = "http://localhost:4200/login";	//	***********************************
		 
		 if(username != null && password != null) {
			 User flag = a.login(username, password);
//			 System.out.println(flag.toString());

			 if(flag != null) {
				 session.setAttribute("uid", flag.getUser_id());
				 session.setAttribute("username", flag.getUsername());
				 session.setAttribute("password", flag.getPassword());
				 session.setAttribute("firstname", flag.getFirst_name());
				 session.setAttribute("lastname", flag.getLast_name());
				 session.setAttribute("email", flag.getEmail());
				 session.setAttribute("imgHash", flag.getHash());
				 session.setAttribute("birthdate", flag.getBirthdate());
				 System.out.println("You have successfully logged in.");
				 request.setAttribute("username", username);
				 page = "http://localhost:4200/home";			//	**************************************
			 } 
		 }
		 
		 	response.sendRedirect(page);
//		 	request.getRequestDispatcher(page).include(request, response);
	 	}
}
