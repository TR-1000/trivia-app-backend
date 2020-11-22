package dao;

import models.User;

public interface LoginDAO {
	
	public User player_login(String username, String password);
	public User admin_login(String username, String password);
	
}
