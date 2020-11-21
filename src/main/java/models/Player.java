package models;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
	private long score;
	private List<Round> rounds_played = new ArrayList<>();


	public Player() {
		super();
		this.setRole(Role.PLAYER);
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
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
		return "Player [score=" + score + ", games_played="
				+ rounds_played + ", getPassword()="
				+ getPassword() + ", getId()=" + getId()
				+ ", getRole()=" + getRole()
				+ ", getUsername()=" + getUsername()
				+ ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}


	
}
