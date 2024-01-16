package cmc.interaction;

import java.util.Scanner;

public class Driver {
	
	private static void adminMenu() {
		System.out.println("There should be an admin menu here for managing users.");
		System.exit(0);
	}

	private static void topMenu() {
		System.out.println("Welcome to Choose My College (CMC)!");
		System.out.println("Please log in.");
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);

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
		while (true) {
			if (UserInteraction.getLoggedInUser() == null)
				topMenu();
			else
				adminMenu();
		}
	}

}
