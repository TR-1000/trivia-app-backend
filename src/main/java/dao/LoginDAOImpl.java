package dao;

import java.sql.Connection;

import models.Admin;
import models.Player;
import models.User;

import utilities.ConnectionUtil;
import java.sql.*;

public class LoginDAOImpl implements LoginDAO {

	private static final RoundDAO roundDAO = new RoundDAOImpl();
	
	
	@Override
	public User player_login(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM player WHERE username = '"+ username +"' AND password = '" + password + "';";
			System.out.println(sql);

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				Player user = new Player();
				user.setId(result.getInt("id"));
				
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setRounds_played(roundDAO.getRoundsByPlayerID(result.getInt("id")));

				return user;
			}

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	
	
	
	@Override
	public User admin_login(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM admin WHERE username = '"+ username +"' AND password = '" + password + "';";
			System.out.println(sql);

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				User user = new Admin();
				
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));

				return user;
			}

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}

}
