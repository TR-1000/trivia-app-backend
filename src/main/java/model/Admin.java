package model;

public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(String username, String password) {
		super(username, password);
		this.setRole(Role.ADMIN);
	}

}
