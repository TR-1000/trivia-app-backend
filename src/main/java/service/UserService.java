package service;

import java.util.List;
import java.util.Set;

import dao.UserDAO;
import dao.UserDAOImpl;
import models.Admin;
import models.Player;
import models.User;

public class UserService {
	private final UserDAO userDAO = new UserDAOImpl();
	
	public boolean insert(User user) {
		return userDAO.insert(user);
		
	}
	
}
