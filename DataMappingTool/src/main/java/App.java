import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App {
	public static ResourceBundle dataConfig = ResourceBundle.getBundle("dataSource");

	public static void main(String[] args) {
		System.out.println("Hello World!");


		String userName = dataConfig.getString("ecc.ok.database.username");

		System.out.println(userName);

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.237.89.143:1521:tj11gdb5", "ECCOK_30D_0702_04",
					"ECCOK_30D_0702_04");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from dual");
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
