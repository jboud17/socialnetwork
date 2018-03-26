package com.revature.beans;

/**
 * 
 * User bean has an id, hash, first name, last name, username, password, email and birthdate field
 * Users table is created in the database
 * 
 * Group 2 - Trevor Fortner, Josh Bordeau, Pooja Suresh, Sonam Sherpa, JR
 * 
 */

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="userSeq")
	@SequenceGenerator(allocationSize=1, name="userSeq", sequenceName="USER_SEQ")
	@Column(name="USER_ID")
	int user_id;

	@Column(name="IMG_HASH")
	String hash;
	
	@Column(name="FIRST_NAME")
    String first_name;
	
	@Column(name="LAST_NAME")
    String last_name;
	
	@Column(name="USERNAME")
    String username;
	
	@Column(name="PASSWORD")
    String password;
	
	@Column(name="EMAIL")
    String email;
	
	@Column(name="BIRTHDATE")
    Timestamp birthdate; //timestamp

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String first_name, String last_name, String username, String password, String hash,
			String email, Timestamp birthdate) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.hash = hash;
		this.email = email;
		this.birthdate = birthdate;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", hash=" + hash + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", username=" + username + ", password=" + password + ", email="
				+ email + ", birthdate=" + birthdate + "]";
	}
}
