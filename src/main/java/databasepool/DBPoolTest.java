package databasepool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DBPoolTest {
	private static BasicDataSource ds = null;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cloud_study?useCursorFetch=true";
	private static final String USER = "root";
	private static final String PASSWORD = "fairy1994";
	public static void dbPoolInit()
	{
		ds = new BasicDataSource();
		ds.setDriverClassName(JDBC_DRIVER);
		ds.setUrl(DB_URL);

		ds.setUsername(USER);
		ds.setPassword(PASSWORD);
	}
	public  void dbPoolTest()
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
	
			stmt.executeQuery("select *from user");
			while(rs.next())
			{
				System.out.println(rs.getString("userName"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}

		}
	}
	public static void main(String[] args) {
		dbPoolInit();
		
		new DBPoolTest().dbPoolTest();
	}
}
