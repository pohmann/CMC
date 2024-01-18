package cmc.interaction;

import java.util.Scanner;

public class Driver {
	
	/**
	 * Get the selected menu option based on user entry.
	 * This reads one line from the provided Scanner.
	 * 
	 * @param s the Scanner from which to read the user's input
	 * @param minChoice the minimum allowed option (inclusive)
	 * @param maxChoice the maximum allowed option (inclusive)
	 * @return the selected integer, or -1 if invalid input is entered
	 */
	private static int getMenuOption(Scanner s, int minChoice, int maxChoice) {
		String choice = s.nextLine();
		try {
			int numChoice = Integer.parseInt(choice);
			if (numChoice < minChoice || numChoice > maxChoice)
				throw new NumberFormatException("Invalid selection");
			return numChoice;
		}
		catch (Exception e) {
			// here if either a non-integer is entered or it is outside
			// the legal range of values (per min/maxChoice)
			return -1;
		}
	}
	
	private static void adminUserListMenu() {
		
	}
	
	private static void adminMenu(Scanner s) {
		System.out.println("----------");
		System.out.println("Admin Menu");
		System.out.println("----------");
		
		System.out.println("Choose an option:");
		System.out.println("1: View List of Users");
		System.out.println("2: Logout");
		
		int choice = getMenuOption(s, 1, 2);
		while (choice == -1) {
			System.out.println("Invalid option.");
			System.out.println("Choose an option:");
			System.out.println("1: View List of Users");
			System.out.println("2: Logout");
			choice = getMenuOption(s, 1, 2);
		}
		
		if (choice == 2) {
			UserInteraction.logout();
		}
	}

	private static void topMenu(Scanner s) {
		System.out.println("Welcome to Choose My College (CMC)!");
		System.out.println("Please log in.");

		String username = "";
		while (username.isBlank()) {
			System.out.print("Username: ");
			username = s.nextLine();
		}

		System.out.print("Password: ");
		String password = s.nextLine();

		boolean success = UserInteraction.login(username, password);
		if (success)
			System.out.println("Redirecting to main menu.");
	}

	// main just forever prints the relevant menu
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while (true) {
			if (UserInteraction.getLoggedInUser() == null)
				topMenu(s);
			else
				adminMenu(s);
		}
	}

}
