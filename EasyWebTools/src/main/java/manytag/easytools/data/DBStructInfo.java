package manytag.easytools.data;

import java.util.HashMap;
import java.util.Map;

public class DBStructInfo {
	private Map<String, DBTableInfo> tables;

	public DBStructInfo() {
		tables = new HashMap<String, DBTableInfo>();
	}

	public Map<String, DBTableInfo> getTables() {
		return tables;
	}

	public void setTables(Map<String, DBTableInfo> tables) {
		this.tables = tables;
	}

	@Override
	public String toString() {
		String ret = "";
		for (String key : tables.keySet()) {
			ret += (tables.get(key).toString() + "\r\n");
		}
		return ret;
	}
}