package manytag.easytools.data;

public class DBPrimaryKey {
	private String columnName;
	private short keySeq;
	private String pkName;
	private String typeName;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public short getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(short keySeq) {
		this.keySeq = keySeq;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		String res = "columnName=" + columnName + "," + "keySeq=" + keySeq + "," + "pkName=" + pkName;
		return res;
	}
}