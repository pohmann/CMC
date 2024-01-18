package cmc.interaction;

import cmc.CMCException;
import cmc.controllers.LoginController;

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
