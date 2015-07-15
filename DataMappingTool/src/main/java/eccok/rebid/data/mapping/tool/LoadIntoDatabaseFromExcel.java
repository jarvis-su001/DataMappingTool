package eccok.rebid.data.mapping.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jarvis
 * @Date Jul 15, 2015
 *
 */
public class LoadIntoDatabaseFromExcel implements IConfiguration {
    String fileName = "D:/git/DataMappingTool/src/main/resources/documents/ECCOK_Data Mapping_temp.xlsx";

    public static void main(String[] args) {
        Connection eccOkConn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
                    ecc_ok_database_username,
                    ecc_ok_database_password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
