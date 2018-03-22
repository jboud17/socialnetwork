package com.revature.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import com.revature.beans.Post;
import com.revature.beans.User;

public interface UserDAO {

	// put sql statement check for user in db
	
	public boolean makeUser(int user_id, String first_name, String last_name, String username, String password, String email, Timestamp birthdate);
	public User login(String username, String password);
	public void updateDetails(String username, String first_name, String last_name, String email, Timestamp birthdate);
	public void resetPassword(String username, String password);
	public void emailUser(String email, String password);
	public void changePic(int user_id, Blob new_pic);
	public User viewMyProfile(String username);
	public void viewAProfile(User user);
	public void viewFeed();
	public boolean likePost(Post post);
	public boolean logout();
}
