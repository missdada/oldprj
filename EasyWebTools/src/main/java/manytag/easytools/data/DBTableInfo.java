package manytag.easytools.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBTableInfo {
	private String tableName;
	private String remarks;
	private List<DBColumnInfo> columns;
	private Map<String, DBPrimaryKey> primaryKey;
	private TableExtendInfo extendInfo;

	public DBTableInfo() {
		columns = new ArrayList<DBColumnInfo>();
		primaryKey = new HashMap<String, DBPrimaryKey>();
		extendInfo = new TableExtendInfo();
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<DBColumnInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<DBColumnInfo> columns) {
		this.columns = columns;
	}

	public Map<String, DBPrimaryKey> getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Map<String, DBPrimaryKey> primaryKey) {
		this.primaryKey = primaryKey;
	}

	public TableExtendInfo getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(TableExtendInfo extendInfo) {
		this.extendInfo = extendInfo;
	}

	@Override
	public String toString() {
		String ret = "tableName=" + tableName + "," + "remarks=" + remarks + ":" + "\r\n";
		for (DBColumnInfo column : columns) {
			ret += column.toString();
		}
		return ret;
	}
}