package com.revature.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.beans.Post;

public interface PostDAO {
	public List<Object> getAllPosts();
	public List<Post> getPostsByUserID(int userId);
	public List<Post> getPostsOfLoggedInUser(HttpSession httpSession);
	public int postLikes();
}
