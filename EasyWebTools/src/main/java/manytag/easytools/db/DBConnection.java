package manytag.easytools.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manytag.easytools.pub.Constants;
import manytag.easytools.pub.SystemConfig;

public class DBConnection {
	public DBConnection() {
		init();
	}

	public void init() {
		try {
			Class.forName(Constants.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return getConnection(SystemConfig.getInstance().getDbConnectStr());
	}

	public Connection getConnection(String url) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}