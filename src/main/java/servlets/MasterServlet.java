package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.LoginController;
import dao.QuestionDAO;
import dao.QuestionDAOImpl;
import dao.RoundDAO;
import dao.RoundDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import dto.ScoreDTO;
import models.Admin;
import models.Player;
import models.Question;
import models.Role;
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
	private static final QuestionDAO questionDAO = new QuestionDAOImpl();

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


			// ================================================
			// ///////////////// LOGIN LOGOUT /////////////////
			// ================================================

			case "player_login":

				loginController.player_login(req, res);

				break;

			case "admin_login":

				loginController.admin_login(req, res);

				break;

			case "logout":

                            if (session != null && ((Boolean) session.getAttribute("loggedin"))) {

				loginController.logout(req, res);

                            } else {

				res.getWriter().println("Can't log out if you were never logged in");
                            }

                            break;

			case "session":

				if (session != null && ((Boolean) session.getAttribute("loggedin"))) {

					String username = (String) session.getAttribute("username");
					Role role = (Role) session.getAttribute("role");

					res.getWriter().println("Logged in as " + role + ": "+ username);

				} else {

                                    res.getWriter().println("You are not logged in");
				}

				break;

			// ============================================
			// ///////////////// PLAYERS /////////////////
			// ============================================

			case "player":

				/* create new player account */

				if (req.getMethod().equals("POST")) {

					BufferedReader reader = req.getReader();

					StringBuilder string = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						string.append(line);
						line = reader.readLine();
					}

					String body = new String(string);

					Player player = om.readValue(body, Player.class);

					System.out.println(userDAO.findPlayerByName(player.getUsername()) != null);
					System.out.println("SANITY CHECK");
					try {

						if (userDAO.findPlayerByName(player.getUsername()) != null) {
							res.setStatus(400);
							res.getWriter().println("Player already exists");
						} else {
							if (userDAO.insert(player)) {
								res.setStatus(201);
								String json = om.writeValueAsString(userDAO.findPlayerByName(player.getUsername()));
								res.getWriter().println(json);
							} else {
								res.setStatus(400);
								res.getWriter().println("Mistakes Were Made");
							}
						}

					} catch (Exception e) {
						res.setStatus(400);
						res.getWriter().println("Mistakes Were Made");
						res.getWriter().println(e);
						e.printStackTrace();
					}

				/* get player by id */

				} else {

					if (uri_portions.length == 2) {

						Long id = Long.parseLong(uri_portions[1]);

						if (session != null && ((Boolean) session.getAttribute("loggedin"))
								&& (
								session.getAttribute("user_id").equals(id)
								||
								session.getAttribute("role").equals(Role.ADMIN)
								)
							)
						{
							Player player = userDAO.findPlayerById(id);

							if (player != null) {
								res.setStatus(200);
								String json = om.writeValueAsString(player);
								res.getWriter().println(json);

							} else {
								res.setStatus(404);
								res.getWriter().println("Player " + id + " doesn't exist");

							}



						} else {
							res.setStatus(401);
							res.getWriter().println("Access Denied!");
						}

					} else {

						/* find all players (Admin only) */

						if ( session != null ) {
							if (session.getAttribute("role").equals(Role.ADMIN)) {
								List<User> all = userDAO.findAllPlayers();
								res.setStatus(200);
								res.getWriter().println(om.writeValueAsString(all));

							} else {
								res.setStatus(401);
								res.getWriter().println(om.writeValueAsString("Access Denied!"));
							}

						} else {
							res.setStatus(401);
							res.getWriter().println(om.writeValueAsString("Access Denied!"));
						}

					}

				}

				break;

			case "player_update":
				System.out.println(session.getAttribute("role"));
				System.out.println(session.getAttribute("username"));

				/* update player account */

				try {

					Long id = Long.parseLong(uri_portions[1]);

					if (session != null && ((Boolean) session.getAttribute("loggedin"))
							&& (
							session.getAttribute("user_id").equals(id)
							||
							session.getAttribute("role").equals(Role.ADMIN)
							)
						)
					{

						Player foundUser = userDAO.findPlayerById(id);

						if (foundUser != null) {

							BufferedReader reader = req.getReader();

							StringBuilder string = new StringBuilder();

							String line = reader.readLine();

							while (line != null) {
								string.append(line);
								line = reader.readLine();
							}

							String body = new String(string);

							System.out.println(body);

							User user = om.readValue(body, Player.class);

							System.out.println(user);

							if(user != null) {

								foundUser.setUsername(user.getUsername());

								foundUser.setPassword(user.getPassword());

								System.out.println("before " + foundUser);

								userDAO.updatePlayer(foundUser);

								System.out.println("after " + foundUser);

								foundUser = userDAO.findPlayerById(id);


							}
							res.setStatus(200);
							res.getWriter().println("Update Successful!");
							String json = om.writeValueAsString(foundUser);
							res.getWriter().println(json);

						} else {
							res.setStatus(404);
							res.getWriter().println("Player doesn't exist");
						}

					} else {
						res.setStatus(401);
						res.getWriter().println("Access Denied!");
					}

				} catch (Exception e) {
					res.setStatus(401);
					res.getWriter().println("Mistakes Were Made");
					res.getWriter().println(e);
				}

				break;

			case "player_delete":

				/* check if the id param was included in the url ex. player_delete/1 */
				try {
					if (uri_portions.length == 2) {

						Long id = Long.parseLong(uri_portions[1]);

						System.out.println(session.getAttribute("user_id").getClass());
						System.out.println(id.getClass());

						/* check if player or admin is logged in */
						if (session != null && ((Boolean) session.getAttribute("loggedin"))
								&& (
								session.getAttribute("user_id").equals(id)
								||
								session.getAttribute("role").equals(Role.ADMIN)
								)
							)
						{

							if(userDAO.findPlayerById(id) != null) {

								userDAO.deletePlayer(id);

								res.getWriter().println("Player " + id + " deleted");

								if (session.getAttribute("role").equals(Role.PLAYER)) {

									loginController.logout(req, res);
								}


							} else {
								res.setStatus(404);
								res.getWriter().println("Player " + id + " doesn't exist");
							}



						} else {
							res.setStatus(401);
							res.getWriter().println("Not Authorized");
						}

					} else {
						res.setStatus(404);
						res.getWriter().println(om.writeValueAsString("Page not found"));
					}
				} catch (Exception e) {

				}

				break;



			// ==========================================
			// ///////////////// ADMIN /////////////////
			// ==========================================

			case "admin":

				/* Only Admin can create new admin account */


				if (req.getMethod().equals("POST")) {

					if (session != null && ((Boolean) session.getAttribute("loggedin"))
							&& (session.getAttribute("role").equals(Role.ADMIN)))
					{
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
								//String json = om.writeValueAsString(userDAO.findAdminByName(admin.getUsername()));
								//res.getWriter().println(json);
							} else {
								res.setStatus(400);
								res.getWriter().println("Mistakes Were Made");
							}

						} catch (Exception e) {
							res.setStatus(400);
							res.getWriter().println("Mistakes Were Made");
							res.getWriter().println(e);
							e.printStackTrace();
						}
					} else {
						res.setStatus(401);
						res.getWriter().println("Only administrators allowed");

					}

				} else {
					res.setStatus(400);
					res.getWriter().println("Mistakes Were Made");
					res.getWriter().println("Maybe this wasn't a POST request");
				}



				break;

			case "admin_update":

				/* update player account */

				try {
					String username = uri_portions[1];

					if (session != null && ((Boolean) session.getAttribute("loggedin"))
							&& (session.getAttribute("username").equals(username)
							&& session.getAttribute("role").equals("admin"))) {

						Admin foundUser = userDAO.findAdminByName(username);

						BufferedReader reader = req.getReader();

						StringBuilder string = new StringBuilder();

						String line = reader.readLine();

						while (line != null) {
							string.append(line);
							line = reader.readLine();
						}

						String body = new String(string);

						System.out.println(body);

						User user = om.readValue(body, Admin.class);

						System.out.println(user);

						if(user != null) {
							foundUser.setUsername(user.getUsername());
							foundUser.setPassword(user.getPassword());
							//foundUser.setId(id);

							System.out.println("before " + foundUser);

							userDAO.updateAdmin(foundUser);

							System.out.println("after " + foundUser);

							foundUser = userDAO.findAdminByName(username);

							System.out.println(body);

						}
						res.setStatus(200);
						res.getWriter().println("Update Successful!");
						String json = om.writeValueAsString(foundUser);
						res.getWriter().println(json);

					} else {

						res.setStatus(401);
						res.getWriter().println("Access Denied!");

					}

				} catch (Exception e) {
					res.getWriter().println("Mistakes Were Made");
					res.getWriter().println(e);

				}

				break;


			// =================================================
			// ///////////////// ROUNDS PLAYED ////////////////
			// =================================================

			case "rounds_played":

				/* Rounds by player name */

				try {

					if (uri_portions.length == 2) {

						String username = uri_portions[1];
						Player foundUser = userDAO.findPlayerByName(username);
						long id = foundUser.getId();

						try {
							List<Round> rounds = roundDAO.getRoundsByPlayerID(id);
							System.out.println(rounds);
							res.setStatus(200);
							res.getWriter().println("Rounds played by " + username);
							String json = om.writeValueAsString(rounds);
							res.getWriter().println(json);

						} catch (Exception e) {
							res.getWriter().println("Mistakes Were Made");
							e.printStackTrace();
							res.setStatus(404);
						}

					} else {

						/* Get all rounds played */

						try {

							List<Round> rounds = roundDAO.getAllRounds();
							res.setStatus(200);
							res.getWriter().println(om.writeValueAsString(rounds));

						} catch (Exception e) {

							res.getWriter().println("Mistakes Were Made");
							e.printStackTrace();
							res.setStatus(400);

						}
					}

				} catch (Exception e) {

					res.getWriter().println("Mistakes Were Made");
					e.printStackTrace();
					res.setStatus(400);

				}

				break;

			case "round":

				/* add a round of trivia */

				if (req.getMethod().equals("POST")) {

					if (session != null && ((Boolean) session.getAttribute("loggedin"))
							&& session.getAttribute("role").equals(Role.PLAYER)) {

						String username = (String) session.getAttribute("username");
						System.out.println(username);

						Long id = (Long) session.getAttribute("user_id");
						System.out.println(id);

						BufferedReader reader = req.getReader();

						StringBuilder string = new StringBuilder();

						String line = reader.readLine();

						while (line != null) {
							string.append(line);
							line = reader.readLine();
						}

						String body = new String(string);

						ScoreDTO scoreDTO = om.readValue(body, ScoreDTO.class);

						long score = scoreDTO.getScore();

						try {

							Round round = new Round(id,score);

							if (roundDAO.newRound(round)) {

								res.setStatus(201);
								res.getWriter().println(
										"Thanks for playing, " + username + "\n" +
										"ID: " + id + "\n" +
										"Score: " + score
									);
								String json = om.writeValueAsString(roundDAO.getRoundsByPlayerID(id));
								res.getWriter().println(json);

							} else {

								res.getWriter().println("Mistakes Were Made");
								res.setStatus(400);

							}

						} catch (Exception e) {

							res.getWriter().println("Mistakes Were Made");
							res.getWriter().println(e);
							e.printStackTrace();

						}

					} else {
						res.getWriter().println("Please Log in to play");
						res.setStatus(401);
					}

				} else {
					res.getWriter().println("Wrong method. Try POST");
				}

				break;

			case "questions":
				
				if (session != null && ((Boolean) session.getAttribute("loggedin"))
				&& (session.getAttribute("role").equals(Role.ADMIN)))
				{
					try {

						List<Question> q = questionDAO.getAllQuestion(); 
					
						String json = om.writeValueAsString(q);

						res.getWriter().println(json);

						res.setStatus(200);

					} catch (Exception e) {
						res.getWriter().println(e);
						System.out.println(e);
					}

				} else {
					res.setStatus(401);
					res.getWriter().println("Admin Only");
				}

				

				break;

			case "":
				res.getWriter().println("Welcome to Trivia");
				break;




			default:
				String json = om.writeValueAsString("Page not found");
				res.setStatus(404);
				res.getWriter().println(json);
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
