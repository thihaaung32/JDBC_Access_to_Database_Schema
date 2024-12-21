package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JdbcConnection
 * 
 * Helper class to get JDBC connection
 *
 * 	   11/24/2024 - thihaaung - modified
 *
 */

public class JdbcConnection {
	static Connection _myConnection;
	
	/**
	 * Default Constructor
	 */
	public JdbcConnection() {
		super();
		
	}
	
	public static Connection getConnection() {
		// get the default JDBC Connection
		if (_myConnection == null) {
			// if null, need to initialize
			try {
			    Class.forName("com.mysql.cj.jdbc.Driver");
			    String sourceURL = "jdbc:mysql://localhost:3306/mydb";
			    String user = "root";
			    String password = "student";
			    _myConnection = DriverManager.getConnection(sourceURL,user, password );
			    System.out.println("Connected Connection"); }
			catch (ClassNotFoundException cnfe) {
			    System.err.println(cnfe); }
			catch (SQLException sqle) {
			    System.err.println(sqle);
			}
		}
		
		return _myConnection;
	}

	void resetConnection() {
		if (_myConnection != null) {
			try {
				_myConnection.close();
			}
			catch (SQLException se) {
				System.out.println("Error while closing connection: " + se.toString());
			}
		}
		
		_myConnection = null;
	}
}
