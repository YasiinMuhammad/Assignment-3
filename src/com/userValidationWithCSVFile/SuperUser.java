package com.userValidationWithCSVFile;

public class SuperUser extends UserLogin{
	
	public SuperUser (String username, String password, String name) {
		this.setRole("super_user");
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}

}
