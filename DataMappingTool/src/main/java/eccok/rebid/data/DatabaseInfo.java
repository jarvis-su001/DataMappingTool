/**
 * @Date : Jul 10, 2015
 */
package eccok.rebid.data;

import java.util.List;

import eccok.utils.Utils;

/**
 * @author Jarvis
 * @Date Jul 10, 2015
 *
 */
public class DatabaseInfo {

	private List<TableGroup> tableGroups;

	public List<TableGroup> getTableGroups() {
		return tableGroups;
	}

	public void setTableGroups(List<TableGroup> tableGroups) {
		this.tableGroups = tableGroups;
	}

	@Override
	public String toString() {
		StringBuffer databaseInfo = new StringBuffer();
		for (TableGroup group : tableGroups) {
			databaseInfo.append(group.toString());
			databaseInfo.append(System.lineSeparator());
		}
		return databaseInfo.toString();
	}

}
