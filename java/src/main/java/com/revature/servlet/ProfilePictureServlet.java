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

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOClass;
import com.revature.util.FrontController;
import com.revature.util.S3Bucket;

public class ProfilePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LogoutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
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
		String imgHash = "";
		
		for(FileItem result : formresults) {
			byte[] fileByteArray = result.get();
			S3Bucket s3 = new S3Bucket();
			imgHash = s3.uploadToS3(fileByteArray);
		}
		
		UserDAO userDao = new UserDAOClass();
		userDao.changePic(userID, imgHash);
		
		log.info("User "+userID+" updated their profile picture");
		resp.sendRedirect("http://localhost:4200/home");	
	}
}
