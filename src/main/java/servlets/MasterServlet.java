package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.LoginController;
import dao.RoundDAO;
import dao.RoundDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

import models.Admin;
import models.Player;
import models.Round;
import models.User;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ObjectMapper om = new ObjectMapper();
	private static final LoginController loginController = new LoginController();
	private static final UserDAO userDAO = new UserDAOImpl();
	private static final RoundDAO roundDAO = new RoundDAOImpl();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		
		res.setContentType("application/json");
		
		final String URI = req.getRequestURI().replace("/TriviaAPI/", "");
		
		String[] uri_portions = URI.split("/");
		
		System.out.println(Arrays.toString(uri_portions));
		
		try {
			HttpSession session = req.getSession(false);
			
			switch (uri_portions[0]) {
			
			// ============================================
			// ///////////////// REGISTER /////////////////
			// ============================================
			case "players":
				if (req.getMethod().equals("POST")) {
					
					BufferedReader reader = req.getReader();

					StringBuilder string = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						string.append(line);
						line = reader.readLine();
					}

					String body = new String(string);

					System.out.println(body);
					
					try {
						
						Player player = om.readValue(body, Player.class);
						System.out.println(player);
						if (userDAO.insert(player)) {
							
							res.setStatus(201);
							res.getWriter().println("Registration Successful!");
							String json = om.writeValueAsString(userDAO.findPlayerByName(player.getUsername()));
							res.getWriter().println(json);
						} else {
							res.getWriter().println("Mistakes Were Made");
						}
						
					} catch (Exception e) {
						res.getWriter().println("Mistakes Were Made");
						res.getWriter().println(e);
						e.printStackTrace();
					}
				}
				
				break;
				
				
			case "admin":
				if (req.getMethod().equals("POST")) {
					
					BufferedReader reader = req.getReader();

					StringBuilder string = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						string.append(line);
						line = reader.readLine();
					}

					String body = new String(string);

					System.out.println(body);
					
					try {
						
						Admin admin = om.readValue(body, Admin.class);
						System.out.println(admin);
						if (userDAO.insert(admin)) {
							
							res.setStatus(201);
							res.getWriter().println("Registration Successful!");
							//String json = om.writeValueAsString(userDAO.findCustomerByEmail(user.getEmail()));
							//res.getWriter().println(json);
						} else {
							res.getWriter().println("Mistakes Were Made");
						}
						
					} catch (Exception e) {
						res.getWriter().println("Mistakes Were Made");
						res.getWriter().println(e);
						e.printStackTrace();
					}
				}
				
				break;
				
				
			case "rounds_played":
				try {
					List<Round> rounds = roundDAO.getRoundByPlayerID(1);
					
					System.out.println(rounds);
					
					res.setStatus(200);

					String json = om.writeValueAsString(rounds);
					
					res.getWriter().println(json);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			default:
				break;
			}
			
			
		} catch (NumberFormatException e) {
			res.getWriter().println("Mistakes Were Made");
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Lets us do POST requests in goGet
		System.out.println("doGet");
		doGet(req, res);
		
	}

}
