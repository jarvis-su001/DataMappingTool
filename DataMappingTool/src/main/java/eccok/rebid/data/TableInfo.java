/**
 * @Date : Jul 10, 2015
 */
package eccok.rebid.data;

import java.util.ArrayList;
import java.util.List;

import eccok.utils.Utils;

/**
 * @author Jarvis
 * @Date Jul 10, 2015
 *
 */
public class TableInfo {
	private String tableName ;
	private String tableComment ;
	private boolean isEmptyTable ;

	private List<ColumnInfo> columns;


	public TableInfo() {
		super();
		this.tableName = "";
		this.tableComment = "";
		this.isEmptyTable = false;
		this.columns = new ArrayList<ColumnInfo>();
	}

	public TableInfo(String tableName, String tableComment, boolean isEmptyTable, List<ColumnInfo> columns) {
		super();
		this.tableName = tableName;
		this.tableComment = tableComment;
		this.isEmptyTable = isEmptyTable;
		this.columns = columns;
	}

	@Override
	public String toString() {
		StringBuffer tableInfo = new StringBuffer();

		for (ColumnInfo column : columns) {
			tableInfo.append(tableName);
			tableInfo.append(Utils.separator);

			tableInfo.append(column.toString());

			tableInfo.append(Utils.separator);
			tableInfo.append(isEmptyTable);

			tableInfo.append(Utils.separator);
			tableInfo.append(tableComment);
			tableInfo.append(System.lineSeparator());
		}

		return tableInfo.toString();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public List<ColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnInfo> columns) {
		this.columns = columns;
	}

	public boolean isEmptyTable() {
		return isEmptyTable;
	}

	public void setEmptyTable(boolean isEmptyTable) {
		this.isEmptyTable = isEmptyTable;
	}
}
