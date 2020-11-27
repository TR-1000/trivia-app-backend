package dao;

import java.util.*;

import dto.PlayerDTO;
import models.User;
import models.Admin;
import models.Player;

public interface UserDAO {
	public boolean insert(User user);
	public boolean updatePlayer(Player pla);
	public boolean updateAdmin(Admin admin);
	public boolean deletePlayer(Long id);
	public PlayerDTO findPlayerByName(String username);
	public PlayerDTO findPlayerById(Long id);
	public Admin findAdminByName(String username);
	public Player adminFindPlayerByName(String username);
	public Player adminFindPlayerById(Long id);
	public List<User> adminFindAllPlayers();
	public List<PlayerDTO> findAllPlayers();
	public List<User> findAllAdmin();
	
	
	

	
}
