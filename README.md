# Trivia-API
This API is for managing players, administrators, and rounds of trivia.
to get an idea of how the trivia game is to be played see this sample console app. More info about the game can be found [here](https://github.com/TR-1000/trivia_game).

Documentation for making requests to the API can be found [here](https://documenter.getpostman.com/view/8437872/TVmFifEd).


* Players can register and login.
* Players can view and update their detailed information (username, password, and rounds played)
Logged in players can create/play a round of trivia.
* Only a current admin can create another admin account but, Admin cannot a player account.
* Admins can both view all player information, as well as update it.
* Anyone can view rounds played by an individual player or all players.

## Using the API

To run the project you will need:
* Java SE Development Kit 8
* Eclipse IDE
* Apache Maven
* Tomcat v8.5 Server
* PostgreSQL Database
* Postman API Testing Tool

To install the project to you to local machine you will need:
* [Download](https://github.com/TR-1000/trivia-api/archive/master.zip) project source code and unzip it into a folder location.
* [Import Maven Project Into Eclipse](https://github.com/TR-1000/trivia-api/blob/master/Import_Maven_Project_Into_Eclipse-Javapapers.pdf)

* In the [src/main/java/utilities/ConnectionUtil.java](https://github.com/TR-1000/trivia-api/blob/master/src/main/java/utilities/ConnectionUtil.java) file
change `String username` and `String password` to your credentials:
```Java
public class ConnectionUtil {

public static Connection getConnection() throws SQLException {

		try {
			//Lets Tomcat see where the driver is
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:postgresql://localhost:5432/trivia";
		String username = "YOUR POSTGRESQL USERNAME";
		String password = "YOUR PASSWORD";

		return DriverManager.getConnection(url, username, password);

	}

}
```
* Run the SQL scripts in PG admin or DBeaver to create database and tables

* Add test data

* [Start the Tomcat server in Eclipse](https://github.com/TR-1000/trivia-api/blob/master/Tomcat_Configuration_In_Eclipse-Baeldung.pdf)

* Make request in Postman

### Known Issues

* Currently there is code for adding questions and answers to the database and access them from the API but that code isn't working yet.

### Roadmap

* Parse questions from the [triva_data.json](https://github.com/TR-1000/trivia-api/blob/master/trivia_data.json) file and add the to the database programmatically.
