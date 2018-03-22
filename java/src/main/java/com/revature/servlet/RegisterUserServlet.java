package com.revature.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;
import com.revature.dao.UserDAOClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub

			String id = req.getParameter("id");
			int user_id = Integer.parseInt(id);

			String fname = req.getParameter("first_name");
			String lname = req.getParameter("last_name");
			String uname = req.getParameter("username");
			String password = req.getParameter("pswd");
			String email = req.getParameter("email");

			Timestamp bd = null;
			
			UserDAOClass a = new UserDAOClass();
			
			a.makeUser(user_id, fname, lname, uname, password, email, bd);
			
			try {
				resp.sendRedirect("http://localhost:4200/home");	//*************************************
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
