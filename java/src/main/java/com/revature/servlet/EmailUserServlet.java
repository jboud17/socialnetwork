package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDAOClass;

/**
 * Servlet implementation class EmailUser
 */
public class EmailUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//do nothing test
		HttpSession session = request.getSession();
		
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 UserDAOClass emailObj = new UserDAOClass();
		 emailObj.emailUser(email, password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 UserDAOClass emailObj = new UserDAOClass();
		 emailObj.emailUser(email, password);
		 
		 //doGet(request, response); do not
	}

}
