package cmc.controllers;

import java.util.List;

import cmc.CMCException;

public class UserController {
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private UserController() throws CMCException {
		throw new CMCException("Attempt to instantiate a UserController");
	}

	// this ADMIN ONLY method returns the list of all the users (and their data)
	// TODO: shouldn't this return a List of User objects?
	public static List<String[]> getAllUsers() {
		List<String[]> usersList = DatabaseController.getAllUsers();
		return usersList;
	}

}
