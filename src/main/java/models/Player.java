package models;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
	
	private List<Round> rounds_played = new ArrayList<>();


	public Player() {
		super();
		this.setRole(Role.PLAYER);
	}

	public List<Round> getRounds_played() {
		return rounds_played;
	}

	public void setRounds_played(List<Round> rounds_played) {
		this.rounds_played = rounds_played;
	}

	public Player(String username, String password) {
		super(username, password);
		this.setRole(Role.PLAYER);
	}

	@Override
	public String toString() {
		return "Player [rounds_played=" + rounds_played
				+ "]";
	}


	
}
