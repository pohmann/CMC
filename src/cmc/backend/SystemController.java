package cmc.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cmc.CMCException;

public class SystemController {
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private SystemController() throws CMCException {
		throw new CMCException("Attempt to instantiate a SystemController");
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
	
	// this REGULAR USER ONLY method attempts to retrieve the list of saved
	// schools for the provided username
	public static List<String> getSavedSchools(String user) {
		Map<String, List<String>> usersToSavedSchools = DatabaseController.getUserSavedSchoolMap();
		return usersToSavedSchools.get(user);
	}

}
