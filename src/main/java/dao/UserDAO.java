package dao;

import java.util.*;

import models.User;
import models.Admin;
import models.Player;


public interface UserDAO {
	
	public boolean insert(User user);
//	public boolean update(User user);
//	public Set<User> findAllPlayers();
//	public Set<Admin> findAllAdmins();
//	public Player findPlayerByID(int id);
	public Player findPlayerByName(String username);
//	public User login(String username, String password);

	
}
