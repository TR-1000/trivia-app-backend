package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import models.Player;
import models.Role;
import models.User;
import utilities.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static final RoundDAO roundDAO = new RoundDAOImpl();
	
	@Override
	public boolean insert(User user) {
		
		String sql;
		
		Role role = user.getRole();
		System.out.println(role);
		if(role == Role.PLAYER) {
			sql = "INSERT INTO player (username, password) " + "VALUES(?,?)";
		} else {
			sql = "INSERT INTO admin (username, password) " + "VALUES(?,?)";
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			int index=0;
			System.out.println(sql);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			System.out.println("Adding user " + user.getUsername() + " to database");

			statement.execute();
			return true;


		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
		

	}

	@Override
	public Player findPlayerByName(String username) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM player WHERE username = '" + username + "' ;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				Player player = new Player();
				player.setId(result.getInt("id"));
				player.setUsername(result.getString("username"));
				player.setPassword(result.getString("password"));
				player.setScore(result.getLong("score"));		
				player.setRounds_played(roundDAO.getRoundByPlayerID(result.getInt("id")));

				return player;
			}

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	


	
	
	
	

}
