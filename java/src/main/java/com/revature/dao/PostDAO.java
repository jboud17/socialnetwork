package com.revature.dao;

import java.util.List;

import com.revature.beans.Post;

public interface PostDAO {
	public List<Object> getAllPosts();
	public List<Post> getPostsByUserID(int userId);
	public int postLikes();
}
