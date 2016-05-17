
package databasepool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class TransactionTest {
	private static BasicDataSource ds = null;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/cloud_study?useCursorFetch=true";
	private static final String USER = "root";
	private static final String PASSWORD = "fairy1994";
	public static void transferAccount()
	{ 
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement("update user set account = ? where userName =?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "zhangsan");
			ptmt.executeQuery();
			ptmt.setInt(1, 100);
			ptmt.setString(2, "Lisi");
			ptmt.executeQuery();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn !=null)
			{
				try {
					conn.rollback();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		finally {
			try {
				if(conn != null)
					conn.close();
				if(ptmt != null)
					ptmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e2) {
				// TODO: handle exception
			}

		}
	}
}
