/**
 * @Date : Jul 13, 2015
 */
package eccok.rebid.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jarvis
 * @Date Jul 13, 2015
 *
 */
public class TableGroup {
	private String groupName;
	private String[] tableNames;

	private List<TableInfo> tables;

	public TableGroup() {
		super();
		this.groupName = "NA";
		this.tableNames = new String[] {};
		this.tables = new ArrayList<TableInfo>();
	}

	public TableGroup(String groupName, String[] tableNames, List<TableInfo> tables) {
		super();
		this.groupName = groupName;
		this.tableNames = tableNames;
		this.tables = tables;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<TableInfo> getTables() {
		return tables;
	}

	public void setTables(List<TableInfo> tables) {
		this.tables = tables;
	}

	public String[] getTableNames() {
		return tableNames;
	}

	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}

	@Override
	public String toString() {
		StringBuffer tableGroup = new StringBuffer();
		for (TableInfo table : tables) {
			tableGroup.append(groupName);
			tableGroup.append(table.toString());
		}

		return tableGroup.toString();
	}

}
