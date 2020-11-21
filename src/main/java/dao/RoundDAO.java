package dao;

import java.util.List;

import models.Round;

public interface RoundDAO {
	public List<Round> getRoundByPlayerID(long id);
}
