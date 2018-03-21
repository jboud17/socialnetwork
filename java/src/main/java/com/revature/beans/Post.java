package com.revature.beans;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POSTS")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="postSeq")
	@SequenceGenerator(allocationSize=1, name="postSeq", sequenceName="POST_SEQ")
	
	@Column(name="POST_ID")
	int post_id;
	
	@Column(name="HASH")
	String hash;
	
	@Column(name="POST_TEXT")
	String post_text;	// what is the text of the post

	@Column(name="IMAGE")
	Blob image; 			// blob

	@Column(name="USER_ID")
	int user_id;	// who made this post

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Post(int post_id, String post_text, Blob image, int user_id) {
		super();
		this.post_id = post_id;
		this.post_text = post_text;
		this.image = image;
		this.user_id = user_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getPost_text() {
		return post_text;
	}

	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
