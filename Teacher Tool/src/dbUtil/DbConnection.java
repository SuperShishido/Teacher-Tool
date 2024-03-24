package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static final String sqConn = "jdbc:sqlite:users.sqlite";
	
	public static Connection getConnection() throws SQLException{
		
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(sqConn);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
