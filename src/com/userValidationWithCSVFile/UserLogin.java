package com.userValidationWithCSVFile;

public class UserLogin implements Comparable<UserLogin> {

	private String username;
	private String password;
	private String name;
	protected String role;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;

	}

	protected void setRole(String role) {
		this.role = role;
	}

	@Override
	public int compareTo(UserLogin that) {

		return 0;
	}

}