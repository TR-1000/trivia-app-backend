package dao;

import java.time.LocalDateTime;
import models.Round;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import utilities.ConnectionUtil;

public class RoundDAOImpl implements RoundDAO {

	
	public List<Round> getRoundByPlayerID(long id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM round WHERE player_id = " + id + ";";

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
