/**
 * 
 */
package applicat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establish connection To db_mastermind Database
 * @author hp
 */
public class MyConnection {
	/**
	 * @param url : url for DB , Timezone fixed to UTC (to avoid Timezone Error)
	 * 
	 */
	private static String url = "jdbc:mysql://localhost:3306/db_mastermind?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String pwd = "";
	private static Connection connection = null;
	
	/**
	 * Default Constructor
	 * @throws ClassNotFoundException Exception
	 */
	private MyConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connected!!");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Connection Creator and getter
	 * @return The Connection
	 * @throws ClassNotFoundException Exception
	 */
	public static Connection getConnection() throws ClassNotFoundException {
		if(connection == null) {
			new MyConnection();
		}
		return connection;
	}
	/**
	 * Stops the Connection
	 */
	public static void stop() {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
