package com.tew.model;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8420789334117957380L;
	
	private String login;
	private String name;
	
	public User(String l, String n) {
		// TODO Auto-generated constructor stub
		this.login = l;
		this.name = n;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
