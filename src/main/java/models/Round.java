package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Round {
	long id;
	long player_id;
	long score;
	String date;
	
	public Round(long id, long score, String date, long player_id) {
		this.id = id;
		this.score = score;
		this.date = date;
		this.player_id =player_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(long player_id) {
		this.player_id = player_id;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Round [id=" + id + ", player_id="
				+ player_id + ", score=" + score + ", date="
				+ date + "]";
	}
	
	
}
