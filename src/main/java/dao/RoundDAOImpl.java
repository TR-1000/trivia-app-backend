package dao;

import models.Round;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utilities.ConnectionUtil;

public class RoundDAOImpl implements RoundDAO {
	
	
	// =================================================
	// ///////////SAVE A NEW ROUND PLAYED /////////////
	// =================================================
	
	@Override
	public boolean newRound(Round round) {
		System.out.println("Adding round of trivia");
		try(Connection conn = ConnectionUtil.getConnection()){
			int index=0;
			String sql = "INSERT INTO round(player_id, score) " + "VALUES(?,?)";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(++index, round.getPlayer_id());
			statement.setLong(++index, round.getScore());

			System.out.println("Adding round to database");

			statement.execute();
			return true;


			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}
	
	// ==========================================================
	// ///////////////// FIND ALL ROUNDS PLAYED ////////////////
	// ==========================================================

	@Override
	public List<Round> getAllRounds() {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM round;";

			Statement statement = conn.createStatement();

			List<Round> roundList = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				roundList.add(new Round(
						result.getLong("id"),
						result.getLong("score"),
						result.getString("date"),
						result.getLong("player_id"))
					);
			}

			return roundList;
		} catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	// ====================================================
	// //////////////GET ROUNDS BY PLAYER ID///////////////
	// ====================================================
	
	@Override
	public List<Round> getRoundsByPlayerID(long id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM round WHERE player_id = " + id + " ORDER BY id DESC;";

			Statement statement = conn.createStatement();
			
			List<Round> roundList = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				roundList.add(new Round(
						result.getLong("id"),
						result.getLong("score"),
						result.getString("date"),
						result.getLong("player_id"))
					);
			}
			
			System.out.println(roundList);
			
			return roundList;

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	
	
	
	
	

}
