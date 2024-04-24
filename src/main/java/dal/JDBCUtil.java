package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		final String url="jdbc:mysql://localhost:3306/website_ban_quan_ao"; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url,"root","");
		} catch (Exception e) {
			System.out.println("cant open database");
			e.printStackTrace();
		}
		return c;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!= null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
