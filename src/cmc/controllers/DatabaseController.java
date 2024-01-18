package cmc.controllers;
import java.util.ArrayList;
import java.util.List;

import cmc.CMCException;
import dblibrary.project.csci230.*;

/**
 * The DatabaseController class is the primary interaction class with the
 * database library.  It currently just calls the lower-level methods and
 * forwards the result (possibly throwing some exceptions along the way).
 * 
 * @author Sally Sparrow
 */
public class DatabaseController {
	// TODO: we'll need to update this to our team's actual database someday!
	private static UniversityDBLibrary database = new UniversityDBLibrary("csci230", "csci230");

	// add a user to the db
	// TODO: it would be nice if this could take a User object instead
	// (so "higher-abstraction" classes don't have to worry about the order
	//  of properties)
	public static boolean addUser(String username, String password, char type,
			String firstName, String lastName) throws CMCException {
		int result = database.user_addUser(firstName, lastName, username, password, type);
		
		if (result == -1) {
			throw new CMCException("Error adding user to the DB");
		}
		else {
			return true;
		}
	}
	
	// remove a user from the db
	public static boolean removeUser(String username) throws CMCException {
		int result = database.user_deleteUser(username);
		if (result != 1) {
			// TODO: How can we tell the difference?
			throw new CMCException("Error removing user from the DB.  Not present?  DB error?");
		}
		else {
			return true;
		}
	}
	
	// get a user; null if not in DB
	public static String[] getUser(String username) {
		String[][] databaseUserStrings = database.user_getUsers();
		
		for (String[] singleUser : databaseUserStrings) {
			String thisUsername = singleUser[2];
			if (thisUsername.equals(username)) {
				return singleUser;
			}
		}
		
		return null;
	}
	
	// get the list of all the users in the DB
	public static List<String[]> getAllUsers() {
		String[][] dbUserList = database.user_getUsers();
		
		ArrayList<String[]> result = new ArrayList<String[]>();
		for (String[] user : dbUserList) {
			result.add(user);
		}
		
		return result;
	}
	
}