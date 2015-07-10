/**
 * @Date : Jul 10, 2015
 */
package eccok.rebid.data;

import eccok.utils.Utils;

/**
 * @author Jarvis
 * @Date Jul 10, 2015
 *
 */
public class ColumnInfo {
	private String columnName;
	private String dataType;
	private int dataLength;
	private String columnComment;

	public ColumnInfo() {
		super();
		this.columnName = "";
		this.dataType = "";
		this.dataLength = 0;
		this.columnComment = "";
	}

	public ColumnInfo(String columnName, String dataType, int dataLength, String columnComment) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.columnComment = columnComment;
	}

	@Override
	public String toString() {
		StringBuffer column = new StringBuffer();

		column.append(columnName);
		column.append(Utils.separator);
		column.append(dataType);
		column.append(Utils.separator);
		column.append(dataLength);
		column.append(Utils.separator);
		column.append(columnComment);

		return column.toString();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
