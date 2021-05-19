package com.example.demo.entity;

public class Login {
	private String userName;
	private String pass;
	
	public Login(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}

	
}