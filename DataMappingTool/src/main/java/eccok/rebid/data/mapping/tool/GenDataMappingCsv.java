/**
 *
 */
package eccok.rebid.data.mapping.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eccok.rebid.data.ColumnInfo;
import eccok.rebid.data.DatabaseInfo;
import eccok.rebid.data.TableInfo;
import eccok.utils.Utils;

/**
 * @author C5023792
 *
 */
public class GenDataMappingCsv implements IConfiguration {

	public static final String GET_ALL_TABLES = "SELECT * FROM User_Tables u ORDER BY u.TABLE_NAME";
	public static final String GET_GIVEN_TABLE = "SELECT * FROM User_Tables u WHERE u.TABLE_NAME= ?";
	public static final String ANALYZ_TABLE = "analyze table ? compute STATISTICS";
	public static final String GET_TABLE_COLUMNS = "SELECT t1.table_name,"
			+ "       t2.column_name,"
			+ "       t2.DATA_TYPE,"
			+ "       t2.DATA_LENGTH,"
			+ "       t2.COLUMN_ID,"
			+ "       t1.NUM_ROWS,"
			+ "       t3.comments    table_comments,"
			+ "       t4.comments    column_comments"
			+ "  FROM user_tables       t1,"
			+ "       user_tab_columns  t2,"
			+ "       user_tab_comments t3,"
			+ "       user_col_comments t4"
			+ " WHERE t1.table_name = t2.table_name"
			+ "   AND t1.table_name = t3.table_name"
			+ "   AND t2.column_name = t4.column_name"
			+ "   AND t2.table_name = t4.table_name"
			+ "   AND t1.TABLE_NAME= ?"
			+ " ORDER BY t1.table_name,t2.COLUMN_ID";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

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
			getDatabaseInfo(eccOkConn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(eccOkConn);
		}

		try {
			eccInConn = DriverManager.getConnection(ecc_in_database_connection_thin_string,
					ecc_in_database_username,
					ecc_in_database_password);
			getDatabaseInfo(eccInConn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(eccInConn);
		}

	}

	public static DatabaseInfo getDatabaseInfo(Connection conn) {
		DatabaseInfo okDatabase = new DatabaseInfo();

		List<TableInfo> tables = new ArrayList<TableInfo>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String tableName = null;

		try {
			stmt = conn.prepareStatement(GET_ALL_TABLES, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();
			while (rs.next()) {
				tableName = rs.getString("table_name");

				int numRows = rs.getInt("num_rows");
				if (numRows <= 0) {

					PreparedStatement countOfTablePs = null;
					ResultSet countOfTableRs = null;
					try {
						String GET_COUNT_OF_TABLE = "SELECT count(1) FROM " + tableName;
						countOfTablePs = conn.prepareStatement(GET_COUNT_OF_TABLE, ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
						countOfTablePs.setQueryTimeout(2000);
						countOfTableRs = countOfTablePs.executeQuery();
						if (countOfTableRs.next()) {
							numRows = countOfTableRs.getInt(1);
						}

					} finally {
						Utils.releaseDBResource(countOfTableRs, countOfTablePs);
					}

				}

				TableInfo table = getTableInfo(conn, tableName);
				table.setEmptyTable(numRows <= 0);
				System.out.println(table);
				tables.add(table);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(rs, stmt);
		}

		okDatabase.setTables(tables);
		return okDatabase;
	}

	private static TableInfo getTableInfo(Connection conn, String tableName) {
		TableInfo table = new TableInfo();
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(GET_TABLE_COLUMNS, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, tableName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ColumnInfo column = new ColumnInfo();
				column.setColumnName(rs.getString("column_name"));
				column.setDataType(rs.getString("DATA_TYPE"));
				column.setDataLength(rs.getInt("DATA_LENGTH"));
				column.setColumnComment(rs.getString("column_comments"));

				columns.add(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(rs, stmt);
		}

		table.setTableName(tableName);
		table.setColumns(columns);

		return table;
	}

}
