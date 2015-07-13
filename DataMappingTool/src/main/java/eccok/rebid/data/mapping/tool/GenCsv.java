package eccok.rebid.data.mapping.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import eccok.rebid.data.DatabaseInfo;
import eccok.utils.Utils;

public class GenCsv implements IConfiguration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseInforOperations database = new DatabaseInforOperations();

		Connection eccOkConn = null;
		Connection eccInConn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
					ecc_ok_database_username,
					ecc_ok_database_password);
			DatabaseInfo okDatabase = database.getDatabaseInfo(eccOkConn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(eccOkConn);
		}

//		try {
//			eccInConn = DriverManager.getConnection(ecc_in_database_connection_thin_string,
//					ecc_in_database_username,
//					ecc_in_database_password);
//			gen.getDatabaseInfo(eccInConn);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Utils.releaseDBResource(eccInConn);
//		}

	}


	private static void generateCsv(DatabaseInfo databaseInfo){

	}


}
