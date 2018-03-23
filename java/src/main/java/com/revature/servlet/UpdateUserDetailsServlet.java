package com.revature.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession hsession = req.getSession();
		
		int userID = (Integer) hsession.getAttribute("uid");
		
		String fname = req.getParameter("firstname");
		String lname = req.getParameter("lastname");
		String email = req.getParameter("email");
		
		hsession.setAttribute("firstname", fname);
		hsession.setAttribute("lastname", lname);
		hsession.setAttribute("email", email);
		
		UserDAOClass userDao = new UserDAOClass();
		
		userDao.updateDetails(userID, fname, lname, email);

		try {
			resp.sendRedirect("http://localhost:4200/home");	//*************************************
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
