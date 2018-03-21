package java.src.main.java.com.revature.beans;

import java.sql.Blob;
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

	@Column(name="HASH")
	String hash;
	
	@Column(name="FIRST_NAME")
    String first_name;
	
	@Column(name="LAST_NAME")
    String last_name;
	
	@Column(name="USERNAME")
    String username;
	
	@Column(name="PASS_WORD")
    String password;
	
	@Column(name="PROFILE_PIC")
    Blob profile_pic; //blob
	
	@Column(name="EMAIL")
    String email;
	
	@Column(name="AGE")
    int age;
	
	@Column(name="BIRTHDATE")
    Timestamp birthdate; //timestamp

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int user_id, String first_name, String last_name, String username, String password, Blob profile_pic,
			String email, int age, Timestamp birthdate) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.profile_pic = profile_pic;
		this.email = email;
		this.age = age;
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

	public Blob getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(Blob profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
				+ ", username=" + username + ", password=" + password + ", profile_pic=" + profile_pic + ", email="
				+ email + ", age=" + age + ", birthdate=" + birthdate + "]";
	}
}
