package utilities;
import java.sql.*;

public class ConnectionUtil {
	
public static Connection getConnection() throws SQLException {
		
		try {
			//Lets Tomcat see where the driver is
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://localhost:5432/trivia";
		String username = "postgres";
		String password = ""; 
		
		return DriverManager.getConnection(url, username, password);
		
	}

}
