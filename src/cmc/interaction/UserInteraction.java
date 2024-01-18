package cmc.interaction;

import java.util.List;
import java.util.Scanner;

import cmc.CMCException;
import cmc.controllers.LoginController;
import cmc.controllers.UserController;

public class UserInteraction {
	
	private static String loggedInUser = null;

	// other classes should *not* instantiate this class.  It is "pure static".
	private UserInteraction() throws CMCException {
		throw new CMCException("Attempt to instantiate a UserInteraction");
	}

	// attempt to login, print message, and return success or failure
	public static boolean login(String username, String password) {
		boolean result = LoginController.login(username, password);
		if (result == true) {
			System.out.println("Login successful!");
			loggedInUser = username;
		}
		else {
			System.out.println("Login failed!  Incorrect username or password.");
			loggedInUser = null;
		}
		return result;
	}
	
	// returns true if there is a user to log out, otherwise false
	public static boolean logout() {
		if (loggedInUser == null) {
			return false;
		}
		else {
			loggedInUser = null;
			return true;
		}
	}
	
	// for admins, this gets the list of all users in the system
	public static List<String[]> getAllUsers() {
		return UserController.getAllUsers();
	}
	
	// ask the admin for details and then attempt to add a user to the
	// database
	public static boolean addUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();
		System.out.print("Password: ");
		String password = s.nextLine();
		System.out.print("First Name: ");
		String firstName = s.nextLine();
		System.out.print("Last Name: ");
		String lastName = s.nextLine();
		System.out.print("Admin? (Y or N): ");
		boolean isAdmin = false;
		if (s.nextLine().trim().equalsIgnoreCase("y"))
			isAdmin = true;
		
		return UserController.addUser(username, password, firstName, lastName, isAdmin);
	}
	
	// ask the admin for a username and then remove that user from the
	// database
	public static boolean removeUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();

		return UserController.removeUser(username);
	}

	/**
	 * Get the current username for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the username for the logged in user
	 */
	public static String getLoggedInUser() {
		return loggedInUser;
	}

}
