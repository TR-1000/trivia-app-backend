package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.LoginDAOImpl;
import dto.LoginDTO;
import models.Player;
import models.Role;
import models.User;

public class LoginController {

	private final LoginDAOImpl loginDAOImpl = new LoginDAOImpl();
	private static final ObjectMapper om = new ObjectMapper();
	
	// ===================================================
	// ///////////////// PLAYER LOGIN /////////////////
	// ===================================================

		public void player_login(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			if(req.getMethod().equals("POST")) {

				BufferedReader reader = req.getReader();

				StringBuilder s = new StringBuilder();

				String line = reader.readLine();

				while(line != null) {
					s.append(line);
					line=reader.readLine();
				}

				String body = new String(s);

				System.out.println(body);

				LoginDTO log = om.readValue(body, LoginDTO.class);

				Player foundUser = (Player) loginDAOImpl.player_login(log.getUsername(), log.getPassword());


				if (foundUser != null) {
					
					Role role = foundUser.getRole();
					Long user_id = (Long) foundUser.getId();
					String username = foundUser.getUsername();

					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", role);
					session.setAttribute("user_id", user_id);
					session.setAttribute("loggedin", true);
					res.setStatus(200);
					String json = om.writeValueAsString(foundUser);
					res.getWriter().println("Login Successful");
					res.getWriter().println(json);

				} else {
					HttpSession session = req.getSession(false);
					if(session != null) {
						session.invalidate();
					}
					res.setStatus(401);
					res.getWriter().println("Invalid Username or Password");

				}

			} else {
				res.getWriter().println("Something went wrong");
				res.getWriter().println("Check METHOD");
			}

		}





		// ===================================================
		// ///////////////// ADMIN LOGIN /////////////////
		// ===================================================

		public void admin_login(HttpServletRequest req, HttpServletResponse res) throws IOException {
			if(req.getMethod().equals("POST")) {
				
				BufferedReader reader = req.getReader();

				StringBuilder s = new StringBuilder();

				String line = reader.readLine();

				while(line != null) {
					s.append(line);
					line=reader.readLine();
				}

				String body = new String(s);

				System.out.println(body);

				LoginDTO log = om.readValue(body, LoginDTO.class);

				User foundUser = loginDAOImpl.admin_login(log.getUsername(), log.getPassword());
				
				if (foundUser != null) {
					
					Role role = foundUser.getRole();
					Long user_id = (Long) foundUser.getId();
					String username = foundUser.getUsername();

					HttpSession session = req.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", role);
					session.setAttribute("user_id", user_id);
					session.setAttribute("loggedin", true);
					res.setStatus(200);
					String json = om.writeValueAsString(foundUser);
					res.getWriter().println("Login Successful");
					res.getWriter().println(json);
					
				} else {
					HttpSession session = req.getSession(false);
					if(session != null) {
						session.invalidate();
					}
					res.setStatus(401);
					res.getWriter().println("Invalid Username or Password");

				}

			} else {
				res.getWriter().println("Something went wrong");
			}

		}


		// ===========================================
		// ///////////////// LOGOUT /////////////////
		// ===========================================
		
		public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
			HttpSession session = req.getSession(false);

			if(session != null) {
				String username = (String) session.getAttribute("username");
				System.out.println(username);
				try {
					res.getWriter().println("Goodbye, " + username);
					res.setStatus(200);
				} catch (Exception e) {
					System.out.println(e);
					res.getWriter().println("Goodbye!");
				}
				
				session.invalidate();
				res.setStatus(200);

			} else {
				res.setStatus(400);
				res.getWriter().println("You must be logged in to log out.");
			}

		}
}
