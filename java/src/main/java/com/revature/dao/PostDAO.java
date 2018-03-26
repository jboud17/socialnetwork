package com.revature.dao;


/**
 * 
 * Post interface including all post related methods
 * 
 * Group 2 - Trevor Fortner, Josh Bordeau, Pooja Suresh, Sonam Sherpa, JR
 * 
 */
import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.beans.Post;

public interface PostDAO {
	public List<Post> getAllPosts();
	public List<Post> getPostsByUserID(int userId);
	public List<Post> getPostsOfLoggedInUser(HttpSession httpSession);
	public int postLikes();
}
