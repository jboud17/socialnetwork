package com.revature.dao;

public interface UserDAO {

	// put sql statement check for user in db
	
	public boolean makeUser();
	public boolean login(String username, String password);
	public void updateDetails();
	public void resetPassword();
	public boolean changePic();
	public boolean createPost();
	public void viewMyProfile();
	public void viewAProfile();
	public void viewFeed();
	public boolean likePost();
	public boolean logout();
}
