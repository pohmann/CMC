package cmc.controllers;

import cmc.CMCException;
import cmc.user.User;

public class LoginController {
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private LoginController() throws CMCException {
		throw new CMCException("Attempt to instantiate a LoginController");
	}

	/**
	 * Verify whether the username and password provided match a user in the
	 * database.  Return a Boolean indicating yes or no.
	 * 
	 * TODO: how could we distinguish a DB error from a failed login?
	 * 
	 * @param username the username to check
	 * @param password the password to check for matching the username
	 * @return the matching User object if the username and password match
	 * a database entry, or null otherwise
	 */
	public static User login(String username, String password) {
		String[] userData = DatabaseController.getUser(username);
		if (userData == null)
			return null;
		
		User theUser = new User(userData[2], userData[3], userData[4].charAt(0), userData[0],
				userData[1]);
		
		if (theUser.activated != 'Y' || !theUser.password.equals(password)) {
			return null;
		}
		else {
			return theUser;
		}
	}

}
