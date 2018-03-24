package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

public class PostLikes {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="post_likesSeq")
	@SequenceGenerator(allocationSize=1, name="post_likesSeq", sequenceName="POST_LIKES_SEQ")
	
	//both keys into a composite key
	@Column(name="USER_ID")
	int user_id;
	
	@Column(name="POST_ID")
	int post_id;

	public PostLikes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	@Override
	public String toString() {
		return "PostLikes [user_id=" + user_id + ", post_id=" + post_id + "]";
	}

}
