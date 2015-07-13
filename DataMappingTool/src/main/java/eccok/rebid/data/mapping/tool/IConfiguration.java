/**
 *
 */
package eccok.rebid.data.mapping.tool;

import java.util.ResourceBundle;

/**
 * @author C5023792
 *
 */
public interface IConfiguration {
	public static ResourceBundle dataConfig = ResourceBundle.getBundle("dataSource");
	public static ResourceBundle dataMappping = ResourceBundle.getBundle("dataMapping");
	public static ResourceBundle dataGroups = ResourceBundle.getBundle("dataGroups");

	public static String ecc_ok_database_username = dataConfig.getString("ecc.ok.database.username");
	public static String ecc_ok_database_password = dataConfig.getString("ecc.ok.database.password");
	public static String ecc_ok_database_port = dataConfig.getString("ecc.ok.database.port");
	public static String ecc_ok_database_ip = dataConfig.getString("ecc.ok.database.ip");
	public static String ecc_ok_database_sid = dataConfig.getString("ecc.ok.database.sid");
	public static String ecc_ok_database_class = dataConfig.getString("ecc.ok.database.class");

	public static String ecc_in_database_username = dataConfig.getString("ecc.in.database.username");
	public static String ecc_in_database_password = dataConfig.getString("ecc.in.database.password");
	public static String ecc_in_database_port = dataConfig.getString("ecc.in.database.port");
	public static String ecc_in_database_ip = dataConfig.getString("ecc.in.database.ip");
	public static String ecc_in_database_sid = dataConfig.getString("ecc.in.database.sid");
	public static String ecc_in_database_class = dataConfig.getString("ecc.in.database.class");

	public static String ecc_ok_database_connection_thin_string = "jdbc:oracle:thin:@" + ecc_ok_database_ip + ":"
			+ ecc_ok_database_port + ":" + ecc_ok_database_sid;
	public static String ecc_in_database_connection_thin_string = "jdbc:oracle:thin:@" + ecc_in_database_ip + ":"
			+ ecc_in_database_port + ":" + ecc_in_database_sid;
}
