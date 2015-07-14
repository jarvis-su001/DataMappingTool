/**
 * @Date : Jul 13, 2015
 */
package eccok.rebid.data;

/**
 * @author Jarvis
 * @Date Jul 13, 2015
 *
 */
public class CompareResult {

	private String groupName;
	private String okTableName;
	private String okColumn;
	private String okColumnType;
	private int okColumnLength;
	private int okColumnIndex;

	private String inTableName;
	private String inColumn;
	private String inColumnType;
	private int inColumnLength;
	private int inColumnIndex;

	public CompareResult() {
		super();
		this.groupName = "NA";
		this.okTableName = "";
		this.okColumn = "";
		this.okColumnType = "";
		this.okColumnLength = 0;
		this.okColumnIndex = 0;

		this.inTableName = "";
		this.inColumn = "";
		this.inColumnType = "";
		this.inColumnLength = 0;
		this.inColumnIndex = 0;
	}

	public CompareResult(String groupName, String okTableName, String okColumn, String okColumnType,
			int okColumnLength, int okColumnIndex, String inTableName, String inColumn, String inColumnType,
			int inColumnLength, int inColumnIndex) {
		super();
		this.groupName = groupName;
		this.okTableName = okTableName;
		this.okColumn = okColumn;
		this.okColumnType = okColumnType;
		this.okColumnLength = okColumnLength;
		this.okColumnIndex = okColumnIndex;

		this.inTableName = inTableName;
		this.inColumn = inColumn;
		this.inColumnType = inColumnType;
		this.inColumnLength = inColumnLength;
		this.inColumnIndex = inColumnIndex;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOkTableName() {
		return okTableName;
	}

	public void setOkTableName(String okTableName) {
		this.okTableName = okTableName;
	}

	public String getOkColumn() {
		return okColumn;
	}

	public void setOkColumn(String okColumn) {
		this.okColumn = okColumn;
	}

	public String getOkColumnType() {
		return okColumnType;
	}

	public void setOkColumnType(String okColumnType) {
		this.okColumnType = okColumnType;
	}

	public int getOkColumnLength() {
		return okColumnLength;
	}

	public void setOkColumnLength(int okColumnLength) {
		this.okColumnLength = okColumnLength;
	}

	public String getInColumn() {
		return inColumn;
	}

	public void setInColumn(String inColumn) {
		this.inColumn = inColumn;
	}

	public String getInColumnType() {
		return inColumnType;
	}

	public void setInColumnType(String inColumnType) {
		this.inColumnType = inColumnType;
	}

	public int getInColumnLength() {
		return inColumnLength;
	}

	public void setInColumnLength(int inColumnLength) {
		this.inColumnLength = inColumnLength;
	}

	public String getInTableName() {
		return inTableName;
	}

	public void setInTableName(String inTableName) {
		this.inTableName = inTableName;
	}

	public int getOkColumnIndex() {
		return okColumnIndex;
	}

	public void setOkColumnIndex(int okColumnIndex) {
		this.okColumnIndex = okColumnIndex;
	}

	public int getInColumnIndex() {
		return inColumnIndex;
	}

	public void setInColumnIndex(int inColumnIndex) {
		this.inColumnIndex = inColumnIndex;
	}

}
