package com.userValidationWithCSVFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserServer {
	static UserService userService = new UserService();
	static UserLogin[] users = new UserLogin[20];
	static Scanner userInput = new Scanner(System.in);
	static String username;
	static String password;
	static String name;

	public static void main(String[] args) throws IOException {

		BufferedReader dataReader = null;
		try {
			int i = 0;
			String line = null;
			dataReader = new BufferedReader(new FileReader("users.txt"));
			while ((line = dataReader.readLine()) != null) {
				String[] userData = line.split(",");
				UserLogin user = new UserLogin();
				user.setUsername(userData[0]);
				user.setPassword(userData[1]);
				user.setName(userData[2]);
				user.setRole(userData[3]);
				users[i] = user;
				System.out.println(users[i]);
				i++;

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (dataReader != null)
				try {
					dataReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		UserLogin isValidated = null;

		boolean successfulLogin = false;
		int loginAttemps = 0;
		while (loginAttemps < 5 && !successfulLogin) {
			System.out.println("Enter your email: ");
			username = userInput.nextLine();

			System.out.println("Enter your password: ");
			password = userInput.nextLine();

			isValidated = userService.validateUser(users, username, password);

			if (isValidated != null) {
				System.out.println("Welcome: " + isValidated.getName());
				successfulLogin = true;
			} else {
				System.out.println("Invalid login, please try again.");
				loginAttemps++;
				if (loginAttemps >= 5) {
					System.out.println("Too many failed login attempts, you are now locked out.");
				}
			}
		}
		Scanner intSelection = new Scanner(System.in);
		int selectedElement = 0;
		while (selectedElement != 4) {

			if (isValidated != null && " super_user".equals(isValidated.getRole())) {

				System.out.println("Please choose from the following options:");
				System.out.println("(0) Login as another user");
				System.out.println("(1) Update username");
				System.out.println("(2) Update password");
				System.out.println("(3) Update name");
				System.out.println("(4) Exit");
				selectedElement = intSelection.nextInt();

			} else if (isValidated != null && " normal_user".equals(isValidated.getRole())) {
				System.out.println("Please choose from the following options:");
				System.out.println("(1) Update username");
				System.out.println("(2) Update password");
				System.out.println("(3) Update name");
				System.out.println("(4) Exit");
				selectedElement = intSelection.nextInt();

			}

			if (selectedElement == 0) {
				String usernameUpdate = loginNewUser();
				UserLogin userUpdate = userService.switchUser(users, usernameUpdate);

				if (userUpdate == null) {
					System.out.println("Username not found");
				} else {
					isValidated = userUpdate;
				}
				System.out.println("Welcome: " + isValidated.getName());
			}

			else if (selectedElement == 1) {
				updateUsername(isValidated);
			} else if (selectedElement == 2) {
				updatePassword(isValidated);
			} else if (selectedElement == 3) {
				updateName(isValidated);
			} else if (selectedElement == 4) {
				System.out.println("User application terminated");
//				exit(isValidated);
			}
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("users.txt", true));
			Arrays.sort(users);
			Set<BufferedWriter> set = new HashSet<BufferedWriter>();
			for (UserLogin user : users) {
				if (set.add(writer))
					writer.write(userService.updateUser(user));
			}

		} finally {
			if (writer != null)
				writer.close();
		}

		userInput.close();
		intSelection.close();

	}

	public static String loginNewUser() {
		System.out.println("Which user would you like to login as? (Type in a valid username)");
		String usernameUpdate = userInput.nextLine();
		return usernameUpdate;
	}

	public static void updateUsername(UserLogin isValidated) {
		System.out.println("Please type in your new username:");
		String username = userInput.nextLine();
		isValidated.setUsername(username);

	}

	public static void updatePassword(UserLogin isValidated) {
		System.out.println("Please type in your new password:");
		String password = userInput.nextLine();
		isValidated.setPassword(password);
	}

	public static void updateName(UserLogin isValidated) {
		System.out.println("Please type in your new name:");
		String name = userInput.nextLine();
		isValidated.setName(name);

	}

//	public static void exit(UserLogin isValidated) {
//		System.out.println("User application terminated");
//		break;
//	}
}
