package manytag.easytools.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import manytag.easytools.data.DBPrimaryKey;
import manytag.easytools.data.DBStructInfo;
import manytag.easytools.data.DBTableInfo;
import manytag.easytools.data.TableExtendInfo;
import manytag.easytools.pub.SystemConfig;
import manytag.easytools.util.ColumnExtendInfoParser;

public class MySqlDBUtil {
	/**
	 * 设置表的中文名称
	 * 
	 * @param oDBStructInfo
	 */
	public static void setTablesRemark(String schema, DBStructInfo oDBStructInfo) {
		DBConnection oDBCon = new DBConnection();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = oDBCon.getConnection(SystemConfig.getInstance().getDbMySqlSchemaInfoStr());
			stmt = con.createStatement();
			Map<String, DBTableInfo> tableInfos = oDBStructInfo.getTables();
			for (String tableName : tableInfos.keySet()) {
				String sql = "select table_comment from tables where table_schema = '" + schema + "' and table_name='" + tableName + "'";
				rs = stmt.executeQuery(sql);

				if (rs.next()) {
					String tableContent = rs.getString(1);
					if (tableContent != null && !"".equals(tableContent)) {
						int index = tableContent.indexOf(";");
						if (index > 0) {
							String tableNameCh = tableContent.substring(0, index);
							DBTableInfo oTableInfo = tableInfos.get(tableName);
							oTableInfo.setRemarks(tableNameCh);
						} else {
							DBTableInfo oTableInfo = tableInfos.get(tableName);
							oTableInfo.setRemarks(tableContent);
						}

						String remarks = tableInfos.get(tableName).getRemarks();
						if (remarks != null) {
							List<String> remarksArr = DBTableStruct.getRealRemarks(remarks);
							if (remarksArr.size() > 0) {
								tableInfos.get(tableName).setRemarks(remarksArr.get(0));
							}
							if (remarksArr.size() > 1) {
								String extendInfoStr = remarksArr.get(1);
								TableExtendInfo extendInfo = new TableExtendInfo();
								ColumnExtendInfoParser.parse(extendInfoStr, extendInfo);

								Map<String, DBPrimaryKey> primaryKeys = tableInfos.get(tableName).getPrimaryKey();
								if (primaryKeys.size() > 0) {
									String primaryKeyColumnName = "";
									Collection<DBPrimaryKey> dbPrimaryKeys = primaryKeys.values();
									for (DBPrimaryKey oDBPrimaryKey : dbPrimaryKeys) {
										primaryKeyColumnName = oDBPrimaryKey.getColumnName();
										break;
									}
									extendInfo.setUidColumn(primaryKeyColumnName);
								}
								tableInfos.get(tableName).setExtendInfo(extendInfo);
							}
						}
					}
				}
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
			if (stmt != null) {
				try {
					stmt.close();
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
	}
}