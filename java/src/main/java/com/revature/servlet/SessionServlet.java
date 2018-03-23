package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.FrontController;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SessionServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		FrontController.addHeader(resp);
		
		if(session!=null){
			resp.setContentType("application/json");
			resp.getWriter().write("{\"uid\":\""+session.getAttribute("uid")+"\",");
			resp.getWriter().write("\"username\":\""+session.getAttribute("username")+"\",");
			resp.getWriter().write("\"password\":\""+session.getAttribute("password")+"\",");
			resp.getWriter().write("\"firstname\":\""+session.getAttribute("firstname")+"\",");
			resp.getWriter().write("\"lastname\":\""+session.getAttribute("lastname")+"\",");
			resp.getWriter().write("\"email\":\""+session.getAttribute("email")+"\",");
			if(session.getAttribute("imgHash") != null) {
				resp.getWriter().write("\"imgHash\":\""+session.getAttribute("imgHash")+"\",");
			}
			else if(session.getAttribute("imgHash") == null) {
				resp.getWriter().write("\"imgHash\":null,");
			}
			if(session.getAttribute("birthdate") != null) {
				resp.getWriter().write("\"birthdate\":\""+session.getAttribute("birthdate")+"\"}");
			}
			else if(session.getAttribute("birthdate") == null) {
				resp.getWriter().write("\"birthdate\":null}");
			}
		} else {
			resp.setContentType("application/json");
			resp.getWriter().write("{\"uid\":null}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
