package com.revature.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDAOClass;

/**
 * Servlet implementation class UpdateUserDetailsServlet
 */
public class UpdateUserDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		int user_id = Integer.parseInt(id);

		String fname = req.getParameter("first_name");
		String lname = req.getParameter("last_name");
		String uname = req.getParameter("username");
		Blob pp = null;
		String email = req.getParameter("email");
		String age_s = req.getParameter("age");
		int age = Integer.parseInt(age_s);
		Timestamp bd = null;
		
		UserDAOClass a = new UserDAOClass();
		
		a.updateDetails(user_id, fname, lname, uname, pp, email, age, bd);

		try {

			resp.sendRedirect("http://localhost:4200/update");	//*************************************
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
