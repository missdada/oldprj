package manytag.easytools.data;

public class DBColumnInfo {
	private String columnName;
	private int dataType;
	private String typeName;
	private int columnSize;
	private boolean isNullAbled;
	private String defaultValue;
	private int charMaxLen;
	private int indexOfColumn;
	private boolean isAutoIncrement;
	private boolean isPrimaryKey;
	private String remarks;
	private ColumnExtendInfo extendInfo;

	public DBColumnInfo() {
		extendInfo = new ColumnExtendInfo();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public boolean isNullAbled() {
		return isNullAbled;
	}

	public void setNullAbled(boolean isNullAbled) {
		this.isNullAbled = isNullAbled;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getCharMaxLen() {
		return charMaxLen;
	}

	public void setCharMaxLen(int charMaxLen) {
		this.charMaxLen = charMaxLen;
	}

	public int getIndexOfColumn() {
		return indexOfColumn;
	}

	public void setIndexOfColumn(int indexOfColumn) {
		this.indexOfColumn = indexOfColumn;
	}

	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ColumnExtendInfo getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(ColumnExtendInfo extendInfo) {
		this.extendInfo = extendInfo;
	}

	@Override
	public String toString() {
		String ret = "columnName=" + columnName + "," + "dataType=" + dataType + "," + "typeName=" + typeName + ","
				+ "columnSize=" + columnSize + "," + "isNullAbled=" + isNullAbled + "," + "defaultValue=" + defaultValue + ","
				+ "charMaxLen=" + charMaxLen + "," + "indexOfColumn=" + indexOfColumn + "," + "isAutoIncrement=" + isAutoIncrement
				+ "," + "isPrimaryKey=" + isPrimaryKey + "," + "remarks=" + remarks + "\r\n";
		return ret;
	}
}