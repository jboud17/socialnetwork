package com.revature.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import com.revature.beans.Post;
import com.revature.beans.User;

public interface UserDAO {

	// put sql statement check for user in db
	
	public boolean makeUser(int user_id, String first_name, String last_name, String username, String password, Blob profile_pic, String email, int age, Timestamp birthdate);
	public User login(String username, String password);
	public void updateDetails(int user_id, String first_name, String last_name, String username, String password, Blob profile_pic, String email, int age, Timestamp birthdate);
	public void resetPassword(String password);
	public void emailUser(String password);
	public void changePic(Blob new_pic);
	public boolean createPost(String text);
	public void viewMyProfile(int user_id);
	public void viewAProfile(int user_id);
	public void viewFeed();
	public boolean likePost(int post_id);
	public boolean logout();
}
