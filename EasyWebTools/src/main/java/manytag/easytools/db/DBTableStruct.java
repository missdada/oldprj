package manytag.easytools.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manytag.easytools.data.ColumnExtendInfo;
import manytag.easytools.data.DBColumnInfo;
import manytag.easytools.data.DBPrimaryKey;
import manytag.easytools.data.DBStructInfo;
import manytag.easytools.data.DBTableInfo;
import manytag.easytools.pub.Constants;
import manytag.easytools.util.ColumnExtendInfoParser;

public class DBTableStruct {
	private String schema;
	private String tablePattern;

	public DBTableStruct(String schema, String tablePattern) {
		this.schema = schema;
		this.tablePattern = tablePattern;
	}

	/*
	public DBStructInfo getDBStructInfo() {
		DBStructInfo oDBStructInfo = new DBStructInfo();

		DBConnection oDBCon = new DBConnection();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = oDBCon.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			rs = dbmd.getColumns(null, schema, "%", "%");

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				String remarks = rs.getString("REMARKS");
				String columnName = rs.getString("COLUMN_NAME");
				int dataType = rs.getInt("DATA_TYPE");
				String typeName = rs.getString("TYPE_NAME");
				int columnSize = rs.getInt("COLUMN_SIZE");
				boolean isNullAbled = !Constants.NO.equals(rs.getString("IS_NULLABLE"));
				String defaultValue = rs.getString("COLUMN_DEF");
				int charMaxLen = rs.getInt("CHAR_OCTET_LENGTH");
				int indexOfColumn = rs.getInt("ORDINAL_POSITION");
				boolean isAutoIncrement = Constants.YES.equals(rs.getString("IS_AUTOINCREMENT"));

				Map<String, DBTableInfo> tables = oDBStructInfo.getTables();
				if (!tables.containsKey(tableName)) {
					tables.put(tableName, new DBTableInfo());

					ResultSet rsPrimaryKeys = null;
					try {
						rsPrimaryKeys = dbmd.getPrimaryKeys(null, schema, tableName);
						while (rsPrimaryKeys.next()) {
							short keySeq = rsPrimaryKeys.getShort("KEY_SEQ");
							String pkName = rsPrimaryKeys.getString("PK_NAME");
							String columnNamePk = rsPrimaryKeys.getString("COLUMN_NAME");
//							String pkTypeName = rsPrimaryKeys.getString("TYPE_NAME");
							DBTableInfo oTableInfo = tables.get(tableName);
							DBPrimaryKey oDBPrimaryKey = new DBPrimaryKey();
							oTableInfo.getPrimaryKey().put(columnNamePk, oDBPrimaryKey);
							oDBPrimaryKey.setColumnName(columnNamePk);
							oDBPrimaryKey.setKeySeq(keySeq);
							oDBPrimaryKey.setPkName(pkName);
//							oDBPrimaryKey.setTypeName(pkTypeName);

//							System.out.println(tableName + "/" + oDBPrimaryKey);
						}
					} finally {
						if (rsPrimaryKeys != null) {
							try {
								rsPrimaryKeys.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
				DBTableInfo oTableInfo = tables.get(tableName);
				oTableInfo.setTableName(tableName);
				DBColumnInfo oColumnInfo = new DBColumnInfo();
				oTableInfo.getColumns().add(oColumnInfo);

				oColumnInfo.setColumnName(columnName);
				oColumnInfo.setDataType(dataType);
				oColumnInfo.setTypeName(typeName);
				oColumnInfo.setColumnSize(columnSize);
				oColumnInfo.setNullAbled(isNullAbled);
				oColumnInfo.setDefaultValue(defaultValue);
				oColumnInfo.setCharMaxLen(charMaxLen);
				oColumnInfo.setIndexOfColumn(indexOfColumn);
				oColumnInfo.setAutoIncrement(isAutoIncrement);

				oColumnInfo.setRemarks(remarks);
				boolean isPrimary = oTableInfo.getPrimaryKey().containsKey(columnName);
				oColumnInfo.setPrimaryKey(isPrimary);
				if (isPrimary) {
					oTableInfo.getPrimaryKey().get(columnName).setTypeName(typeName);
				}
//				System.out.println(tableName + "/" + remarks + "/" + oColumnInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return oDBStructInfo;
	}
	*/

	public DBStructInfo getDBStructInfo() {
		DBStructInfo oDBStructInfo = new DBStructInfo();

		DBConnection oDBCon = new DBConnection();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = oDBCon.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			rs = dbmd.getTables(null, schema, tablePattern, null);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				String remarks = rs.getString("REMARKS");

				DBTableInfo oTableInfo = new DBTableInfo();
				oTableInfo.setTableName(tableName);
				oTableInfo.setRemarks(remarks);
				oDBStructInfo.getTables().put(tableName, oTableInfo);

				setPrimaryKeyInfo(dbmd, tableName, oTableInfo);
				setColumnInfo(dbmd, tableName, oTableInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return oDBStructInfo;
	}

	private void setColumnInfo(DatabaseMetaData dbmd, String tableName, DBTableInfo oTableInfo) throws SQLException {
		ResultSet rsColumns = null;
		try {
			rsColumns = dbmd.getColumns(null, schema, tableName, "%");

			while (rsColumns.next()) {
				// String tableName = rsColumns.getString("TABLE_NAME");
				String remarks = rsColumns.getString("REMARKS");
				String columnName = rsColumns.getString("COLUMN_NAME");
				int dataType = rsColumns.getInt("DATA_TYPE");
				String typeName = rsColumns.getString("TYPE_NAME");
				int columnSize = rsColumns.getInt("COLUMN_SIZE");
				boolean isNullAbled = !Constants.NO.equals(rsColumns.getString("IS_NULLABLE"));
				String defaultValue = rsColumns.getString("COLUMN_DEF");
				int charMaxLen = rsColumns.getInt("CHAR_OCTET_LENGTH");
				int indexOfColumn = rsColumns.getInt("ORDINAL_POSITION");
				boolean isAutoIncrement = Constants.YES.equals(rsColumns.getString("IS_AUTOINCREMENT"));

				DBColumnInfo oColumnInfo = new DBColumnInfo();
				oTableInfo.getColumns().add(oColumnInfo);

				oColumnInfo.setColumnName(columnName);
				oColumnInfo.setDataType(dataType);
				oColumnInfo.setTypeName(typeName);
				oColumnInfo.setColumnSize(columnSize);
				oColumnInfo.setNullAbled(isNullAbled);
				oColumnInfo.setDefaultValue(defaultValue);
				oColumnInfo.setCharMaxLen(charMaxLen);
				oColumnInfo.setIndexOfColumn(indexOfColumn);
				oColumnInfo.setAutoIncrement(isAutoIncrement);

				List<String> remarksArr = getRealRemarks(remarks);
				if (remarksArr.size() > 0) {
					oColumnInfo.setRemarks(remarksArr.get(0));
				}
				if (remarksArr.size() > 1) {
					String extendInfoStr = remarksArr.get(1);
					ColumnExtendInfo extendInfo = new ColumnExtendInfo();
					ColumnExtendInfoParser.parse(extendInfoStr, extendInfo);
					oColumnInfo.setExtendInfo(extendInfo);
				}

				boolean isPrimary = oTableInfo.getPrimaryKey().containsKey(columnName);
				oColumnInfo.setPrimaryKey(isPrimary);
				if (isPrimary) {
					oTableInfo.getPrimaryKey().get(columnName).setTypeName(typeName);
				}
			}
		} finally {
			if (rsColumns != null) {
				try {
					rsColumns.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setPrimaryKeyInfo(DatabaseMetaData dbmd, String tableName, DBTableInfo oTableInfo) throws SQLException {
		ResultSet rsPrimaryKeys = null;
		try {
			rsPrimaryKeys = dbmd.getPrimaryKeys(null, schema, tableName);
			while (rsPrimaryKeys.next()) {
				short keySeq = rsPrimaryKeys.getShort("KEY_SEQ");
				String pkName = rsPrimaryKeys.getString("PK_NAME");
				String columnNamePk = rsPrimaryKeys.getString("COLUMN_NAME");
				DBPrimaryKey oDBPrimaryKey = new DBPrimaryKey();
				oTableInfo.getPrimaryKey().put(columnNamePk, oDBPrimaryKey);
				oDBPrimaryKey.setColumnName(columnNamePk);
				oDBPrimaryKey.setKeySeq(keySeq);
				oDBPrimaryKey.setPkName(pkName);
			}
		} finally {
			if (rsPrimaryKeys != null) {
				try {
					rsPrimaryKeys.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<String> getRealRemarks(String remarksSrc) {
		List<String> result = new ArrayList<String>();
		if (remarksSrc != null) {
//			String[] split = remarksSrc.split(",");
//			result.add(split[0].trim());
//			if (split.length > 1) {
//				result.add(split[1].trim());
//			}
			int index = remarksSrc.indexOf(',');
			if (index > 0) {
				String s1 = remarksSrc.substring(0, index);
				String s2 = remarksSrc.substring(index + 1, remarksSrc.length());
				result.add(s1);
				result.add(s2);
			} else {
				result.add(remarksSrc);
			}
		}
		return result;
	}
}