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

    private String inTableLevel;
    private String inTableName;
    private String inColumn;
    private String inColumnType;
    private int inColumnLength;
    private int inColumnIndex;

    private String newTableLevel;
    private String newTableName;
    private String newColumn;
    private String newColumnType;
    private int newColumnLength;
    private int newColumnIndex;

    private String versionComments;

    public CompareResult() {
        super();
        this.groupName = "NA";
        this.okTableName = "";
        this.okColumn = "";
        this.okColumnType = "";
        this.okColumnLength = 0;
        this.okColumnIndex = 0;

        this.inTableLevel = "";
        this.inTableName = "";
        this.inColumn = "";
        this.inColumnType = "";
        this.inColumnLength = 0;
        this.inColumnIndex = 0;

        this.newTableLevel = "";
        this.newTableName = "";
        this.newColumn = "";
        this.newColumnType = "";
        this.newColumnLength = 0;
        this.newColumnIndex = 0;

        this.versionComments = "";
    }

    public CompareResult(String groupName, String okTableName, String okColumn, String okColumnType,
            int okColumnLength, int okColumnIndex, String inTableLevel, String inTableName, String inColumn,
            String inColumnType,
            int inColumnLength, int inColumnIndex, String newTableLevel, String newTableName, String newColumn,
            String newColumnType,
            int newColumnLength, int newColumnIndex, String versionComments) {
        super();
        this.groupName = groupName;
        this.okTableName = okTableName;
        this.okColumn = okColumn;
        this.okColumnType = okColumnType;
        this.okColumnLength = okColumnLength;
        this.okColumnIndex = okColumnIndex;

        this.inTableLevel = inTableLevel;
        this.inTableName = inTableName;
        this.inColumn = inColumn;
        this.inColumnType = inColumnType;
        this.inColumnLength = inColumnLength;
        this.inColumnIndex = inColumnIndex;

        this.newTableLevel = newTableLevel;
        this.newTableName = newTableName;
        this.newColumn = newColumn;
        this.newColumnType = newColumnType;
        this.newColumnLength = newColumnLength;
        this.newColumnIndex = newColumnIndex;

        this.versionComments = versionComments;
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

    public String getNewTableName() {
        return newTableName;
    }

    public void setNewTableName(String newTableName) {
        this.newTableName = newTableName;
    }

    public String getNewColumn() {
        return newColumn;
    }

    public void setNewColumn(String newColumn) {
        this.newColumn = newColumn;
    }

    public String getNewColumnType() {
        return newColumnType;
    }

    public void setNewColumnType(String newColumnType) {
        this.newColumnType = newColumnType;
    }

    public int getNewColumnLength() {
        return newColumnLength;
    }

    public void setNewColumnLength(int newColumnLength) {
        this.newColumnLength = newColumnLength;
    }

    public int getNewColumnIndex() {
        return newColumnIndex;
    }

    public void setNewColumnIndex(int newColumnIndex) {
        this.newColumnIndex = newColumnIndex;
    }

    public String getVersionComments() {
        return versionComments;
    }

    public void setVersionComments(String versionComments) {
        this.versionComments = versionComments;
    }

    public String getInTableLevel() {
        return inTableLevel;
    }

    public void setInTableLevel(String inTableLevel) {
        this.inTableLevel = inTableLevel;
    }

    public String getNewTableLevel() {
        return newTableLevel;
    }

    public void setNewTableLevel(String newTableLevel) {
        this.newTableLevel = newTableLevel;
    }

}
