package dao;

import java.util.*;

import models.User;
import models.Admin;
import models.Player;

public interface UserDAO {
	public boolean insert(User user);
	public boolean updatePlayer(Player pla);
	public boolean updateAdmin(Admin admin);
	public boolean deletePlayer(Long id);
	public Player findPlayerByName(String username);
	public Admin findAdminByName(String username);
	public List<User> findAllPlayers();
	public List<User> findAllAdmin();
	public Player findPlayerById(Long id);
	

	
}
