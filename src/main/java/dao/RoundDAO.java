package dao;

import java.util.List;

import models.Round;

public interface RoundDAO {
	public List<Round> getRoundsByPlayerID(long id);
	public List<Round> getAllRounds();
	public boolean newRound(Round round);
}
