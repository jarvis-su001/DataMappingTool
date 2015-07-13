/**
 *
 */
package eccok.rebid.data.mapping.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eccok.rebid.data.ColumnInfo;
import eccok.rebid.data.DatabaseInfo;
import eccok.rebid.data.TableGroup;
import eccok.rebid.data.TableInfo;
import eccok.utils.Utils;

/**
 * @author C5023792
 *
 */
public class DatabaseInforOperations implements IConfiguration {

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

	public DatabaseInfo getDatabaseInfo(Connection conn) {
		DatabaseInfo okDatabase = new DatabaseInfo();
		List<TableGroup> tableGroups = getAllTableGroups();

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

		okDatabase.setTableGroups(tableGroups);
		return okDatabase;
	}

	public List<TableInfo> getTablesInfo(Connection conn, Connection eccOkConn, String state) {
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
				if (numRows <= 0 && "OK".equals(state)) {

					PreparedStatement countOfTablePs = null;
					ResultSet countOfTableRs = null;

					try {
						String getRecordCount = "SELECT * FROM compare_tables  t WHERE t.table_name = ? AND t.state ='OK'";
						countOfTablePs = conn.prepareStatement(getRecordCount, ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_READ_ONLY);
						countOfTablePs.setString(1, tableName);
						countOfTablePs.setQueryTimeout(2000);
						countOfTableRs = countOfTablePs.executeQuery();
						if (countOfTableRs.next()) {
							numRows = countOfTableRs.getInt("TABLE_RECORDS");
						}
					} finally {
						Utils.releaseDBResource(countOfTableRs, countOfTablePs);
					}
					if (numRows <= 0) {

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
				}

				TableInfo table = getTableInfo(conn, tableName);
				table.setRecordCount(numRows);
				table.setEmptyTable(numRows <= 0);
				System.out.println(table);
				tables.add(table);

				saveTableInfo(eccOkConn, table, state);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(rs, stmt);
		}

		return tables;
	}

	static String sql = "INSERT INTO compare_tables VALUES (?,?,?,?,?,?)";

	private void saveTableInfo(Connection conn, TableInfo table, String state) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			for (ColumnInfo column : table.getColumns()) {
				stmt.setString(1, state);
				stmt.setString(2, table.getTableName());
				stmt.setString(3, column.getColumnName());
				stmt.setString(4, column.getDataType());
				stmt.setInt(5, column.getDataLength());
				stmt.setInt(6, table.getRecordCount());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.releaseDBResource(stmt);
		}

	}

	private TableInfo getTableInfo(Connection conn, String tableName) {
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

	private List<TableGroup> getAllTableGroups() {
		List<TableGroup> tableGroups = new ArrayList<TableGroup>();

		TableGroup STATIC_TBLS = new TableGroup();
		STATIC_TBLS.setGroupName("STATIC_TBLS");
		STATIC_TBLS.setTableNames(dataGroups.getString("STATIC_TBLS").split(Utils.tableGroupSeparator));

		TableGroup DATALOAD = new TableGroup();
		DATALOAD.setGroupName("DATALOAD");
		DATALOAD.setTableNames(dataGroups.getString("DATALOAD").split(Utils.tableGroupSeparator));

		TableGroup TRANSACTION_TBLS = new TableGroup();
		TRANSACTION_TBLS.setGroupName("TRANSACTION_TBLS");
		TRANSACTION_TBLS.setTableNames(dataGroups.getString("TRANSACTION_TBLS").split(Utils.tableGroupSeparator));

		TableGroup PAYMENT_TBLS = new TableGroup();
		PAYMENT_TBLS.setGroupName("PAYMENT_TBLS");
		PAYMENT_TBLS.setTableNames(dataGroups.getString("PAYMENT_TBLS").split(Utils.tableGroupSeparator));

		TableGroup Misc_System = new TableGroup();
		Misc_System.setGroupName("Misc-System");
		Misc_System.setTableNames(dataGroups.getString("Misc-System").split(Utils.tableGroupSeparator));

		TableGroup TBLS_NOT_USED = new TableGroup();
		TBLS_NOT_USED.setGroupName("TBLS_NOT_USED");
		TBLS_NOT_USED.setTableNames(dataGroups.getString("TBLS_NOT_USED").split(Utils.tableGroupSeparator));

		return tableGroups;
	}
}
