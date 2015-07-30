import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eccok.utils.Utils;

/**
 * Hello world!
 *
 */
public class TempMain {
    String[] states = { "AL", "CO", "IN", "LA", "OH", "OK", "VA" };

    public static void main(String[] args) {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@127.127.0.1:1522:orcl", "jarvis",
                    "jarvis");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ECC_COLUMNS_ALL");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(rs, stmt, conn);
        }
    }
}
