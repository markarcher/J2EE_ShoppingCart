package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="user")
@Entity
public class User {
	private String userName;
	private String password;
	private int role;
	private int id;

	
	public User() {
				
	}

	public User(String userName, String password, int role) {
		
		this.userName = userName;
		this.password = password;
		this.role = role;
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    @Column(name="userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(int role){
		this.role= role;
	}
	@Column(name="role")
	public int getRole(){
		return role;
	}

}
