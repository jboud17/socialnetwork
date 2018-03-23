package com.revature.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
		
		//split the date
		String[] split = date.split("-");
		int[] splitInNums = new int[3];
		for(int i=0; i<3; i++) {
			splitInNums[i] = Integer.parseInt(split[i]);
		}
		
		//Create timestamp object
		Timestamp birthdate = null;
		
		//Parse the date
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
			Calendar parsedDate = new GregorianCalendar(splitInNums[0], splitInNums[1], splitInNums[2]);
		    birthdate = new Timestamp(parsedDate.getTimeInMillis());
		    System.out.println(birthdate);
		} catch(Exception e) {
			System.out.println("date format failed");
			System.out.println("Attempted date: " + date);
			e.printStackTrace();
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
