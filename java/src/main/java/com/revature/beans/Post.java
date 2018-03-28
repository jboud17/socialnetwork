package com.revature.beans;

/**
 * 
 * Post bean has an id, test, hash and title field
 * Posts table created in database
 * 
 * Group 2 - Trevor Fortner, Josh Bordeau, Pooja Suresh, Sonam Sherpa, JR
 * 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POSTS")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="postSeq")
	@SequenceGenerator(allocationSize=1, name="postSeq", sequenceName="POST_SEQ")
	@Column(name="POST_ID")
	private int post_id;
	
	@Column(name="IMG_HASH")
	private String hash;
	
	@Column(name="POST_TEXT")
	private String post_text;	// what is the text of the post
	
	@Column(name="TITLE")
	private String title;
	
	@ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private User user;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Post(String title, String imgHash, String post_text, User user) {
		super();
		this.title = title;
		this.hash = imgHash;
		this.post_text = post_text;
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String imgHash) {
		this.hash = imgHash;
	}

	public String getPost_text() {
		return post_text;
	}

	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
