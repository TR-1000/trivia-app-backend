package dto;

import java.util.ArrayList;
import java.util.List;

import models.Round;

public class PlayerDTO {
	
	private long id;
	private String username;
	private List<Round> rounds_played = new ArrayList<>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Round> getRounds_played() {
		return rounds_played;
	}
	public void setRounds_played(List<Round> rounds_played) {
		this.rounds_played = rounds_played;
	}
	
	
	@Override
	public String toString() {
		return "PlayerDTO [id=" + id + ", username="
				+ username + ", rounds_played="
				+ rounds_played + "]";
	}
	
	
	
	
}
