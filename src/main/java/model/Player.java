package model;

public class Player extends User {
	private long score;
	private long games_played;


	public Player() {
		super();
	}

	public Player(String username, String password) {
		super(username, password);
		this.setRole(Role.PLAYER);
	}

	@Override
	public String toString() {
		return "Player [score=" + score + ", games_played="
				+ games_played + ", getPassword()="
				+ getPassword() + ", getId()=" + getId()
				+ ", getRole()=" + getRole()
				+ ", getUsername()=" + getUsername()
				+ ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}


	
}
