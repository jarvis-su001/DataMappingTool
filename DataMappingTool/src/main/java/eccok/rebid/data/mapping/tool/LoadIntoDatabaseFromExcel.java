package eccok.rebid.data.mapping.tool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFCell;
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
public class LoadIntoDatabaseFromExcel implements IConfiguration {
    static String fileName = "E:/Document/OKECC/ECCOK_Data Mapping_temp.xlsx";

    public static void main(String[] args) {
        Connection eccOkConn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            eccOkConn = DriverManager.getConnection(ecc_ok_database_connection_thin_string,
                    ecc_ok_database_username,
                    ecc_ok_database_password);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

            int rowstart = xssfSheet.getFirstRowNum() + 1;
            int rowEnd = xssfSheet.getLastRowNum();

            for (int i = rowstart; i <= rowEnd; i++) {
                XSSFRow row = xssfSheet.getRow(i);
                if (null == row)
                    continue;
                CompareResult result = new CompareResult();
                int cellStart = row.getFirstCellNum();
                int cellEnd = row.getLastCellNum();

                XSSFCell cell = row.getCell(0);
                result.setGroupName(row.getCell(0).getStringCellValue());
                result.setOkTableName(row.getCell(1).getStringCellValue());
                result.setOkColumn(row.getCell(2).getStringCellValue());
                result.setOkColumnType(row.getCell(3).getStringCellValue());
                result.setOkColumnLength((int) row.getCell(4).getNumericCellValue());

                result.setInTableLevel(row.getCell(5).getStringCellValue());
                result.setInTableName(row.getCell(6).getStringCellValue());
                result.setInColumn(row.getCell(7).getStringCellValue());
                result.setInColumnType(row.getCell(8).getStringCellValue());
                result.setInColumnLength((int) row.getCell(9).getNumericCellValue());

                result.setNewTableLevel(row.getCell(10).getStringCellValue());
                result.setNewTableName(row.getCell(11).getStringCellValue());
                result.setNewColumn(row.getCell(12).getStringCellValue());
                result.setNewColumnType(row.getCell(13).getStringCellValue());
                result.setNewColumnLength((int) row.getCell(14).getNumericCellValue());

                result.setVersionComments(row.getCell(16).getStringCellValue());

                CompareTables.insertIntoCompareTable(result, eccOkConn);
            }

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            Utils.releaseDBResource(eccOkConn);
        }

    }

}
