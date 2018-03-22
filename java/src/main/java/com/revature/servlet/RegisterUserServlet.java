package com.revature.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

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
		String date = req.getParameter("birthdate");
		
		//sysout date
		System.out.println(date);
		String[] split = date.split("/");
		
		//Create timestamp object
		Timestamp birthdate = null;
		
		//Parse the date
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = (Date) dateFormat.parse(split[2]+"-"+split[1]+"-"+split[0]+" 10:10:10:100");
		    birthdate = new Timestamp(parsedDate.getTime());
		} catch(Exception e) {
			System.out.println("Date formatting failed");
		}

		//Create new user and save in db
		User user = new User(fname, lname, uname, pword, null, email, birthdate);
		session.save(user);
		
		//Transaction commit and session close
		tx.commit();
		session.close();
		
		//Redirect back
		resp.sendRedirect("http://localhost:4200/login");
	}
}
