/**
 * @Date : Jul 13, 2015
 */
package eccok.rebid.data.mapping.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import eccok.rebid.data.ColumnInfo;
import eccok.rebid.data.CompareResult;
import eccok.rebid.data.TableInfo;
import eccok.utils.Utils;

/**
 * @author Jarvis
 * @Date Jul 13, 2015
 *
 */
public class CompareTables implements IConfiguration {

    public static final String GET_ALL_TABLES = "SELECT * FROM User_Tables u WHERE u.TABLE_NAME NOT LIKE 'COMPARE_RESULT%' ORDER BY u.TABLE_NAME ";
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
        List<TableInfo> okTables = null;
        List<TableInfo> inTables = null;
        Connection eccOkConn = null;
        Connection eccInConn = null;
        DatabaseInforOperations database = new DatabaseInforOperations();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
                    ecc_ok_database_username,
                    ecc_ok_database_password);
            String truncateTable = "TRUNCATE TABLE compare_tables";
            eccOkConn.createStatement().executeUpdate(truncateTable);

            truncateTable = "TRUNCATE TABLE compare_result";
            eccOkConn.createStatement().executeUpdate(truncateTable);

            okTables = database.getTablesInfo(eccOkConn, eccOkConn, "OK");

            eccInConn = DriverManager.getConnection(ecc_in_database_connection_thin_string,
                    ecc_in_database_username,
                    ecc_in_database_password);
            inTables = database.getTablesInfo(eccInConn, eccOkConn, "IN");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(eccOkConn);
            Utils.releaseDBResource(eccInConn);
        }

        try {
            eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
                    ecc_ok_database_username,
                    ecc_ok_database_password);

            for (TableInfo okTable : okTables) {
                if (isOkToolTables(okTable.getTableName())) {
                    continue;
                }
                for (ColumnInfo okColumn : okTable.getColumns()) {
                    CompareResult result = new CompareResult();
                    String groupName = dataGroups.getString(okTable.getTableName());
                    if ("".equals(groupName) && okTable.isEmptyTable()) {
                        groupName = "TBLS_NOT_USED";
                    }
                    result.setGroupName(groupName);

                    result.setOkTableName(okTable.getTableName());
                    result.setOkColumn(okColumn.getColumnName());
                    result.setOkColumnType(okColumn.getDataType());
                    result.setOkColumnLength(okColumn.getDataLength());
                    result.setOkColumnIndex(okColumn.getColumnIndex());

                    for (TableInfo inTable : inTables) {
                        if (isSameTable(okTable.getTableName(), inTable.getTableName())) {
                            result.setInTableName(inTable.getTableName());

                            for (ColumnInfo inColumn : inTable.getColumns()) {

                                if (isSameColumn(okTable.getTableName(), okColumn.getColumnName(),
                                        inTable.getTableName(),
                                        inColumn.getColumnName())) {

                                    result.setInColumn(inColumn.getColumnName());
                                    result.setInColumnType(inColumn.getDataType());
                                    result.setInColumnLength(inColumn.getDataLength());
                                    result.setInColumnIndex(inColumn.getColumnIndex());

                                    result.setNewColumn(inColumn.getColumnName());
                                    result.setNewColumnType(inColumn.getDataType());
                                    result.setNewColumnLength(inColumn.getDataLength());
                                    // result.setNewColumnIndex(inColumn.getColumnIndex());
                                }
                            }

                        }

                    }

                    insertIntoCompareTable(result, eccOkConn);
                }

            }

            for (TableInfo inTable : inTables) {
                if (isOkToolTables(inTable.getTableName())) {
                    continue;
                }
                for (ColumnInfo inColumn : inTable.getColumns()) {
                    CompareResult result = new CompareResult();
                    String groupName = dataGroups.getString(inTable.getTableName());
                    result.setGroupName(groupName);
                    result.setInTableName(inTable.getTableName());

                    result.setInColumn(inColumn.getColumnName());
                    result.setInColumnType(inColumn.getDataType());
                    result.setInColumnLength(inColumn.getDataLength());
                    result.setInColumnIndex(inColumn.getColumnIndex());

                    result.setNewColumn(inColumn.getColumnName());
                    result.setNewColumnType(inColumn.getDataType());
                    result.setNewColumnLength(inColumn.getDataLength());

                    for (TableInfo okTable : okTables) {
                        if (isSameTable(okTable.getTableName(), inTable.getTableName())) {
                            result.setOkTableName(okTable.getTableName());
                            for (ColumnInfo okColumn : okTable.getColumns()) {

                                if (isSameColumn(okTable.getTableName(), okColumn.getColumnName(),
                                        inTable.getTableName(),
                                        inColumn.getColumnName())) {
                                    result.setOkColumn(okColumn.getColumnName());
                                    result.setOkColumnType(okColumn.getDataType());
                                    result.setOkColumnLength(okColumn.getDataLength());
                                    result.setOkColumnIndex(okColumn.getColumnIndex());
                                }
                            }

                        }
                    }

                    insertIntoCompareTable(result, eccOkConn);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(eccOkConn);
        }

    }

    static boolean isSameTable(String okTableName, String inTableName) {
        if (okTableName.startsWith("COMPARE_RESULT")) {
            okTableName = "COMPARE_RESULT";
        }

        String mappingInTable = dataMappping.getString(okTableName);

        String mappingOkTable = dataMappping.getString(inTableName);

        if (null == mappingInTable || "NA".equals(mappingInTable)) {
            return false;
        }

        if (null == mappingOkTable || "NA".equals(mappingOkTable)) {
            return false;
        }

        if (null == okTableName || null == inTableName) {
            return false;
        }
        return mappingInTable.equalsIgnoreCase(inTableName) || mappingOkTable.equalsIgnoreCase(okTableName);
    }

    static boolean isSameColumn(String okTableName, String okColumn, String inTableName, String inColumn) {

        String mappingInTableColumn = dataMappping.getString(okTableName + "." + okColumn);
        String mappingOkTableColumn = dataMappping.getString(inTableName + "." + inColumn);

        if (null == okColumn || null == inColumn) {
            return false;
        }
        return mappingInTableColumn.equalsIgnoreCase(inTableName + "." + inColumn)
                || mappingOkTableColumn.equalsIgnoreCase(okTableName + "." + okColumn);
    }

    public static final String insertSQL = "INSERT INTO compare_result VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String selectOKRecordSQL = "SELECT * FROM compare_result c WHERE c.ok_table_name = ? AND c.ok_table_column = ?";
    public static final String selectINRecordSQL = "SELECT * FROM compare_result c WHERE c.in_table_name = ? AND c.in_table_column = ?";
    public static final String truncateTable = "TRUNCATE TABLE compare_result";

    public static void insertIntoCompareTable(CompareResult result, Connection conn) {

        PreparedStatement stmt = null;

        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement stmt3 = null;

        try {

            stmt = conn.prepareStatement(selectOKRecordSQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, result.getOkTableName());
            stmt.setString(2, result.getOkColumn());
            rs = stmt.executeQuery();

            stmt2 = conn.prepareStatement(selectOKRecordSQL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt2.setString(1, result.getInTableName());
            stmt2.setString(2, result.getInColumn());
            rs2 = stmt.executeQuery();

            if (rs.next() || rs2.next()) {

            } else {
                String level = getTableLevel(result.getInTableName());
                stmt3 = conn.prepareStatement(insertSQL);
                stmt3.setString(1, result.getGroupName());
                stmt3.setString(2, result.getOkTableName());
                stmt3.setString(3, result.getOkColumn());
                stmt3.setString(4, result.getOkColumnType());
                stmt3.setInt(5, result.getOkColumnLength());
                stmt3.setInt(6, result.getOkColumnIndex());

                stmt3.setString(7, level);
                stmt3.setString(8, result.getInTableName());
                stmt3.setString(9, result.getInColumn());
                stmt3.setString(10, result.getInColumnType());
                stmt3.setInt(11, result.getInColumnLength());
                stmt3.setInt(12, result.getInColumnIndex());

                stmt3.setString(13, level);
                stmt3.setString(14, result.getNewTableName());
                stmt3.setString(15, result.getNewColumn());
                stmt3.setString(16, result.getNewColumnType());
                stmt3.setInt(17, result.getNewColumnLength());
                stmt3.setInt(18, result.getNewColumnIndex());

                stmt3.setString(19, result.getVersionComments());

                stmt3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(rs, stmt);
            Utils.releaseDBResource(rs2, stmt2);
            Utils.releaseDBResource(stmt3);

        }

    }

    public static void backAndTruncateTable(Connection conn) {

        PreparedStatement stmt4 = null;
        PreparedStatement stmt5 = null;

        String back_sql = "CREATE TABLE compare_result_" + System.currentTimeMillis()
                + " AS SELECT * FROM compare_result";
        try {
            stmt4 = conn.prepareStatement(back_sql);
            stmt4.executeUpdate();
            stmt5 = conn.prepareStatement(truncateTable);
            stmt5.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(stmt4);
            Utils.releaseDBResource(stmt5);
        }

    }

    static boolean isOkToolTables(String tableName) {

        if (tableName.startsWith("COMPARE_RESULT")) {
            return true;
        }
        try {
            dataMappping.getString(tableName);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

        return "COMPARE_TABLES".equalsIgnoreCase(tableName)
                || "COMPARE_RESULT".equalsIgnoreCase(tableName) || tableName.startsWith("COMPARE_RESULT");
    }

    static String getTableLevel(String tableName) {
        String coreTablesList = dataGroups.getString("coreTables");
        String[] coreTables = coreTablesList.split(",");
        for (String core : coreTables) {

            if (core.equalsIgnoreCase(tableName)) {
                return "CORE";
            }
        }
        return "APP";
    }

}
