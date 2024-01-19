package cmc.controllers;

import java.util.ArrayList;
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
	
	// this ADMIN ONLY method attempts to add a user to the database with the
	// provided details
	public static boolean addUser(String username, String password,
			String firstName, String lastName, boolean isAdmin) {
		char type = (isAdmin ? 'a' : 'u');
		try {
			return DatabaseController.addUser(username, password, type, firstName, lastName);
		} catch (CMCException e) {
			// TODO: should we let the calling class report the error more
			//       clearly by passing it on?
			return false;
		}
	}
	
	// this ADMIN ONLY method attempts to remove a user from the database
	// based on the provided username
	public static boolean removeUser(String username) {
		try {
			return DatabaseController.removeUser(username);
		} catch (CMCException e) {
			// TODO: should we let the calling class report the error more
			//       clearly by passing it on?
			return false;
		}
	}
	
	// this REGULAR USER ONLY method searches for schools in the database
	// based on provided criteria (just state for now)
	public static List<String[]> search(String state) {
		List<String[]> schoolList = DatabaseController.getAllSchools();
		
		List<String[]> filteredList = new ArrayList<String[]>();
		for (int i = 0; i < schoolList.size(); i++) {
			String[] school = schoolList.get(i);
			if (school[1].equals(state) || school[1] == "")
				filteredList.add(school);
		}
		
		return filteredList;
	}
	
	// this REGULAR USER ONLY method attempts to add the provided school
	// to the list of saved schools for the provided username
	public static boolean saveSchool(String user, String school) {
		return DatabaseController.saveSchool(user, school);
	}

}
