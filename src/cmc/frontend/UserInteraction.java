package cmc.frontend;

import java.util.List;
import java.util.Scanner;

import cmc.backend.SystemController;
import cmc.backend.User;

public class UserInteraction {
	
	private User loggedInUser;
	
	private SystemController theSystemController;
	
	// Construct a UserInteraction using the basic (no parameter)
	// SystemController as the single underlying controller object.
	// TODO: Someday, we should refactor the single SystemController class
	//       into multiple classes for better organization of functionalities.
	public UserInteraction() {
		this.theSystemController = new SystemController();
		this.loggedInUser = null;
	}

	// attempt to login, print message, and return success or failure
	public boolean login(String username, String password) {
		User result = this.theSystemController.login(username, password);
		if (result != null) {
			System.out.println("Login successful!");
			this.loggedInUser = result;
			return true;
		}
		else {
			System.out.println("Login failed!  Incorrect username or password.");
			this.loggedInUser = null;
			return false;
		}
	}
	
	// returns true if there is a user to log out, otherwise false
	public boolean logout() {
		if (this.loggedInUser == null) {
			return false;
		}
		else {
			this.loggedInUser = null;
			return true;
		}
	}
	
	// for admins, this gets the list of all users in the system
	public List<String[]> getAllUsers() {
		return this.theSystemController.getAllUsers();
	}
	
	// ask the admin for details and then attempt to add a user to the
	// database
	public boolean addUser(Scanner s) {
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
		
		return this.theSystemController.addUser(username, password, firstName, lastName, isAdmin);
	}
	
	// ask the admin for a username and then remove that user from the
	// database
	public boolean removeUser(Scanner s) {
		System.out.print("Username: ");
		String username = s.nextLine();

		return this.theSystemController.removeUser(username);
	}
	
	public List<String[]> search(Scanner s) {
		// TODO: in the future, we would like to support searching by various
		//       criteria, but we'll settle for just state for now
		System.out.print("State (leave blank to not search by this criterion): ");
		String state = s.nextLine();
		
		return this.theSystemController.search(state);
	}
	
	// ask for a school name to save, and attempt to save that school
	// to the list for the currently-logged-in user
	public boolean saveSchool(Scanner s) {
		System.out.print("School Name: ");
		String schoolName = s.nextLine();

		if (this.loggedInUser == null)
			return false;
		else
			return this.theSystemController.saveSchool(this.loggedInUser.username, schoolName);
	}
	
	// get the list of saved school names for the currently-logged-in user
	public List<String> getSavedSchools() {
		return this.theSystemController.getSavedSchools(this.loggedInUser.username);
	}

	/**
	 * Get the current username for the current user logged in via
	 * this UserInteraction class.
	 * 
	 * @return the username for the logged in user
	 */
	public User getLoggedInUser() {
		return this.loggedInUser;
	}

}
