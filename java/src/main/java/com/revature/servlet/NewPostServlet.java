package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.revature.beans.Post;
import com.revature.util.FrontController;
import com.revature.util.HibernateUtil;

public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LogoutServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FrontController.addHeader(resp);
		HttpSession session = req.getSession();
		
		FrontController.addHeader(resp);
		resp.setContentType("text/html");
		
		int userID = (Integer) session.getAttribute("uid");		
		FileItemFactory fileFact = new DiskFileItemFactory();
		ServletFileUpload servfileup = new ServletFileUpload(fileFact);
		List<FileItem> formresults = null;
		try {
			formresults = servfileup.parseRequest(req);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		String posttext = "";
		String imgHash = "";
		
		for(FileItem result : formresults) {
			if(result.isFormField()) {
				if(result.getFieldName().equals("postSummary"))
					posttext = result.getString();
			}
			else {
				byte[] fileByteArray = result.get();
				
//				fileByteArray is the file as bytes, can we upload to S3 from here?
//				create the hash and save it for the new post
//				imgHash = //new hash
			}
		}
		
		Post postToInsert = new Post(imgHash, posttext, userID);
		
		Session hibSession = HibernateUtil.getSession();
		hibSession.save(postToInsert);
		
		log.info("User "+userID+" created a new post with text "+posttext);
		resp.sendRedirect("http://localhost:4200/employeeHome");	
	}
}
