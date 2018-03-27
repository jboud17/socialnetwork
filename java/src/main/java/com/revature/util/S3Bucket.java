package com.revature.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.hibernate.Query;
import org.hibernate.Session;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.revature.beans.Post;

public class S3Bucket {
	
	private static final String bucketName = "rev-grouptwo";
	private static final BasicAWSCredentials creds = new BasicAWSCredentials("AKIAIFJR6G367GAT5EEA", "FRpmPHfDAVA/sZaNK6iP3kya+SzhWOS2bEN4MhH6");
	private static final AmazonS3 s3 = AmazonS3Client
					.builder()
					.withRegion("us-east-1")
					.withCredentials(new AWSStaticCredentialsProvider(creds))
					.build();
	
	public S3Bucket() {}
	
	public String uploadToS3(byte[] fileByteArray) {
		
		//generate random hash
		String hash = this.randomHash() + ".png";
		
		//create path to bucket
		String filePath = "images/"+hash;
		
        try {
		    	//create a temp file
		    	File tf = File.createTempFile("image", ".png"); 
	    		
		    //write image to temp file
	    	    BufferedImage img = ImageIO.read(new ByteArrayInputStream(fileByteArray));
	        ImageIO.write(img, "png", tf);
	        
	        //upload image to s3 bucket
	        s3.putObject(new PutObjectRequest(bucketName, filePath, tf));	
	        
		} catch(IOException e){
			e.printStackTrace();
		}
        
		//return the hash to store in the database
		return hash;
	}
	
	private String randomHash() {
		//set variables for generating random hash
		int leftLimit = 97; //letter 'a'
	    int rightLimit = 122; //letter 'z'
	    int targetStringLength = 12; //hash length
	    
	    //instantiate new random object
	    Random random = new Random();
	    
	    //
		StringBuilder buffer = null;
		String generatedString = null;
		Query query = null;
		Session session = HibernateUtil.getSession();
		
		//create new random hash while it is found in the db
		do {
			buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) 
				(random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			generatedString = buffer.toString();
			query = session.createQuery("FROM Post p WHERE p.hash = :hash");
			query.setString("hash", generatedString+".png");	
		} while(query.uniqueResult() != null);
		
		//close session connection
		session.close();
		
		//return the newly generated hash
	    return generatedString;
	}
}
