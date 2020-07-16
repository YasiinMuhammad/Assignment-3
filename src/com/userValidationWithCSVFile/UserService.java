package com.userValidationWithCSVFile;

public class UserService {

	public UserLogin validateUser(UserLogin[] users, String username, String password) {

		for (UserLogin user : users) {

			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				return user;
			}
		
		}
		return null;
	}
	public UserLogin switchUser(UserLogin[] users, String username) {

		for (UserLogin user : users) {

			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		
		}
		return null;
	}
	public String updateUser(UserLogin user) {
		return user.getUsername() + "," + user.getPassword() + "," + user.getName() + "," + user.getRole() + "\n";
	}
}

