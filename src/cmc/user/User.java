package cmc.user;

public class User {
	public String username;
	public String password;
	public char type; // u or a
	public String firstName;
	public String lastName;
	public char activated; // Y or N

	public User(String username, String password, char type, String firstName,
			String lastName) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.firstName = firstName;
		this.lastName = lastName;
		this.activated = 'Y'; // users always start activated
	}

}
