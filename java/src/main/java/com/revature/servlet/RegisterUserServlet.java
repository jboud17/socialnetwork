package com.revature.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;

import com.revature.beans.User;
import com.revature.dao.UserDAOClass;
import com.revature.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterUserServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Set session and transaction objects
			Session session= HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			
			//Get values from register form
			String fname = req.getParameter("firstName");
			String lname = req.getParameter("lastName");
			String uname = req.getParameter("username");
			String pword = req.getParameter("password");
			String email = req.getParameter("email");
			String formDate = req.getParameter("birthday");
			Timestamp birthdate = null;
			
			//Create new user and save in db
			User user = new User(fname, lname, uname, pword, null, email, birthdate);
			session.save(user);
		}
}
