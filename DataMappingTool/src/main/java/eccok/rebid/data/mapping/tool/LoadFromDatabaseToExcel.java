/**
 * @Date : Jul 15, 2015
 */
package eccok.rebid.data.mapping.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import eccok.rebid.data.CompareResult;
import eccok.utils.Utils;

/**
 * @author Jarvis
 * @Date Jul 15, 2015
 *
 */
public class LoadFromDatabaseToExcel implements IConfiguration {

    public static String sql = "SELECT t.group_name,"
            + "       t.ok_table_name,"
            + "       t.ok_table_column,"
            + "       t.ok_table_column_type,"
            + "       t.ok_table_column_length,"
            + "       t.in_table_level,"
            + "       t.in_table_name,"
            + "       t.in_table_column,"
            + "       t.in_table_column_type,"
            + "       t.in_table_column_length,"
            + "       t.new_table_level,"
            + "       t.new_table_name,"
            + "       t.new_table_column,"
            + "       t.new_table_column_type,"
            + "       t.new_table_column_length,"
            + "       '',"
            + "       t.version_comment"
            + "  FROM compare_result t"
            + "   ORDER BY t.group_name,"
            + "          t.ok_table_name,"
            + "          t.ok_table_column_index,"
            + "          t.in_table_name,"
            + "          t.in_table_column_index";

    /**
     * @param args
     */
    public static void main(String[] args) {
        String fileName = "E:/Document/OKECC/ECCOK_Data Mapping_temp.xlsx";
        List<CompareResult> tables = getCompareResultsFromDatabase();
        writeToExcel(fileName, tables);
    }

    public static List<CompareResult> getCompareResultsFromDatabase() {
        List<CompareResult> results = new ArrayList<CompareResult>();
        Connection eccOkConn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
                    ecc_ok_database_username,
                    ecc_ok_database_password);

            ps = eccOkConn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            while (rs.next()) {
                CompareResult re = new CompareResult();
                re.setGroupName(rs.getString("group_name"));

                re.setOkTableName(rs.getString("ok_table_name"));
                re.setOkColumn(rs.getString("ok_table_column"));
                re.setOkColumnType(rs.getString("ok_table_column_type"));
                re.setOkColumnLength(rs.getInt("ok_table_column_length"));

                re.setInTableLevel(rs.getString("in_table_level"));
                re.setInTableName(rs.getString("in_table_name"));
                re.setInColumn(rs.getString("in_table_column"));
                re.setInColumnType(rs.getString("in_table_column_type"));
                re.setInColumnLength(rs.getInt("in_table_column_length"));

                re.setNewTableLevel(rs.getString("new_table_level"));
                re.setNewTableName(rs.getString("new_table_name"));
                re.setNewColumn(rs.getString("new_table_column"));
                re.setNewColumnType(rs.getString("new_table_column_type"));
                re.setNewColumnLength(rs.getInt("new_table_column_length"));

                re.setVersionComments(rs.getString("version_comment"));

                results.add(re);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(rs, ps, eccOkConn);
        }
        return results;
    }

    public static void writeToExcel(String fileName, List<CompareResult> results) {

        File file = new File(fileName);
        // if (file.exists()) {
        // file.delete();
        // file = new File(fileName);
        // }

        String[] titleArray = { "Group name","OK table name", "OK Column", "OK column Type", "OK column length",
                "IN table Level", " IN table name", "IN Column", "IN column Type", "IN column length",
                "New table Level", " New table name", "New Column", "New column Type", "New column length","  ",
                "Version comments" };
        // 获取参数个数作为excel列数
        int columeCount = titleArray.length ;

//        columeCount = 17;
        // 获取List size作为excel行数
        int rowCount = results.size();

        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet = null;
        xssfWorkbook = new XSSFWorkbook();
        // xssfSheet = xssfWorkbook.getSheetAt(0);
        xssfSheet = xssfWorkbook.createSheet("All_tables");
        XSSFRow headRow = xssfSheet.createRow(0);
        for (int m = 0; m < columeCount; m++) {
            XSSFCell cell = headRow.createCell(m);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            xssfSheet.setColumnWidth(m, 6000);
            XSSFCellStyle style = xssfWorkbook.createCellStyle();
            XSSFFont font = xssfWorkbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            short color = HSSFColor.RED.index;
            font.setColor(color);
            style.setFont(font);
            // 填写数据
            cell.setCellStyle(style);
            cell.setCellValue(titleArray[m]);
        }

        int index = 0;
        // 写入数据
        for (CompareResult compareResult : results) {
            // logger.info("写入一行");
            XSSFRow row = xssfSheet.createRow(index + 1);
            for (int n = 0; n <= columeCount; n++) {
                row.createCell(n);
            }
            row.getCell(0).setCellValue(compareResult.getGroupName());

            row.getCell(1).setCellValue(compareResult.getOkTableName());
            row.getCell(2).setCellValue(compareResult.getOkColumn());
            row.getCell(3).setCellValue(compareResult.getOkColumnType());
            row.getCell(4).setCellValue(compareResult.getOkColumnLength());

            row.getCell(5).setCellValue(compareResult.getInTableLevel());
            row.getCell(6).setCellValue(compareResult.getInTableName());
            row.getCell(7).setCellValue(compareResult.getInColumn());
            row.getCell(8).setCellValue(compareResult.getInColumnType());
            row.getCell(9).setCellValue(compareResult.getInColumnLength());

            row.getCell(10).setCellValue(compareResult.getNewTableLevel());
            row.getCell(11).setCellValue(compareResult.getNewTableName());
            row.getCell(12).setCellValue(compareResult.getNewColumn());
            row.getCell(13).setCellValue(compareResult.getNewColumnType());
            row.getCell(14).setCellValue(compareResult.getNewColumnLength());

            row.getCell(16).setCellValue("NNNNNNNNN");
            row.getCell(17).setCellValue(compareResult.getVersionComments());
            index++;
        }

        // 写到磁盘上
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            xssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
