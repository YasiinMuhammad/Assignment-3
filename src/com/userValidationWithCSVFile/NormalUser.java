package com.userValidationWithCSVFile;

public class NormalUser extends UserLogin {
	
	public NormalUser(String username, String password, String name) {
		this.role = ("normal_user");
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
	}

}
