package databasepool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
public class ReadDatabase {
	//set final parameters
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cloud_study?useCursorFetch=true";
	static final String USER = "root";
	static final String PASSWORD = "fairy1994";
	public static void buy(String productName) throws ClassNotFoundException
	{
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			//load driver program
			Class.forName(JDBC_DRIVER);
			//setup database connection
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//execute sql
			ptmt = conn.prepareStatement("");
			String ProductName = null;
			String Inventory = null;
			rs = ptmt.executeQuery();
			//get result
			while(rs.next())
			{
				ProductName = rs.getString("ProductName");
				Inventory = rs.getString("Inventory");
				//result output
				System.out.println("ProductName:" + ProductName);
				System.out.println("Inventory:" + Inventory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//5.clean environment
			try 
			{
				if(conn != null)
				{
					conn.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
				if(rs != null)
				{
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			helloworld();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
