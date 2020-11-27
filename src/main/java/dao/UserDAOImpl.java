package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import models.Admin;
import models.Player;
import models.Role;
import models.User;
import utilities.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static final RoundDAO roundDAO = new RoundDAOImpl();
	
	
	// ==================================================
	// ///////////////// REGISTER USER /////////////////
	// ==================================================
	
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
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			System.out.println("Adding user " + user.getUsername() + " to database");

			statement.execute();
			return true;


		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
		

	}

	
	
	
	// ========================================================
	// ///////////////// FIND PLAYER BY ID /////////////////
	// ========================================================
	
	@Override
	public Player findPlayerById(Long id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM player WHERE id = '" + id + "' ;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				Player player = new Player();
				player.setId(result.getLong("id"));
				player.setUsername(result.getString("username"));
				player.setPassword(result.getString("password"));	
				player.setRounds_played(roundDAO.getRoundsByPlayerID(result.getInt("id")));

				return player;
			}

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	
	// ========================================================
	// ///////////////// FIND PLAYER BY NAME /////////////////
	// ========================================================
		
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
				player.setRounds_played(roundDAO.getRoundsByPlayerID(result.getInt("id")));

				return player;
			}

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	// =====================================================
	// ///////////////// FIND ALL PLAYERS /////////////////
	// =====================================================

	@Override
	public List<User> findAllPlayers() {
		System.out.println("Finding all players");
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM player ORDER BY id DESC;";

			Statement statement = conn.createStatement();

			List<User> userList = new ArrayList<>();

			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				Player user = new Player();

				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setRounds_played(roundDAO.getRoundsByPlayerID(result.getInt("id")));
				
				userList.add(user);

			}

			return userList;

		}catch(SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	// ========================================================
	// ///////////////// UPDATE PLAYER ///////////////////////
	// ========================================================

	@Override
	public boolean updatePlayer(Player player) {
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "UPDATE player SET  username=?, password=? WHERE id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			
			int index = 0;

			statement.setString(++index, player.getUsername());
			statement.setString(++index, player.getPassword());
			statement.setLong(++index, player.getId());

			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// ========================================================
	// ///////////////// DELETE PLAYER ///////////////////////
	// ========================================================

	@Override
	public boolean deletePlayer(Long id) {
		try(Connection conn = ConnectionUtil.getConnection()){

			String sql = "DELETE from player WHERE id = ?;";

			PreparedStatement statement = conn.prepareStatement(sql);
			System.out.println(statement);
			int index = 0;

			statement.setLong(++index, id);
			statement.execute();
			
			System.out.println("deleting player");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// ========================================================
	// ///////////////// FIND ADMIN BY NAME //////////////////
	// ========================================================
		
	@Override
	public Admin findAdminByName(String username) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM player WHERE username = '" + username + "' ;";

			Statement statement = conn.createStatement();

			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {
				Admin admin = new Admin();
				admin.setId(result.getInt("id"));
				admin.setUsername(result.getString("username"));
				admin.setPassword(result.getString("password"));

				return admin;
			}

		} catch(SQLException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	// =====================================================
	// ///////////////// FIND ALL ADMIN ///////////////////
	// =====================================================

		@Override
		public List<User> findAllAdmin() {
			System.out.println("Finding all players");
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "SELECT * FROM admin ORDER BY id DESC;";

				Statement statement = conn.createStatement();

				List<User> userList = new ArrayList<>();

				ResultSet result = statement.executeQuery(sql);

				while(result.next()) {
					Admin user = new Admin();

					user.setId(result.getInt("id"));
					user.setUsername(result.getString("username"));
					user.setPassword(result.getString("password"));
					userList.add(user);
				}

				return userList;

			}catch(SQLException e) {
				System.out.println(e);
			}
			return null;
		}
		
		// ========================================================
		// ///////////////// UPDATE ADMIN ///////////////////////
		// ========================================================

		@Override
		public boolean updateAdmin(Admin admin) {
			try(Connection conn = ConnectionUtil.getConnection()){

				String sql = "UPDATE admin SET  username=?, password=? WHERE username = ?;";

				PreparedStatement statement = conn.prepareStatement(sql);
				
				int index = 0;

				statement.setString(++index, admin.getUsername());
				statement.setString(++index, admin.getPassword());
				statement.setString(++index, admin.getUsername());

				statement.execute();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	

}
