package aop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private ConnectionFactory() {
	}

	private static class ConnHolder {
		private static Connection conn;
		static {
			String url = "jdbc:mysql://localhost:3306/hibernate?user=root&password=036330";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url);
				System.out.println("Connection initialized.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		return ConnHolder.conn;
	}
	
	public static void close() {
		if(ConnHolder.conn != null) {
			try {
				ConnHolder.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setAutoCommit(boolean autoCommit) throws SQLException {
		ConnHolder.conn.setAutoCommit(autoCommit); 
	}
	
	public static void commit() {
		try {
			ConnHolder.conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollBack() {
		try {
			ConnHolder.conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
