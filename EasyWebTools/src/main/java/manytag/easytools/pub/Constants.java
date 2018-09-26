package manytag.easytools.pub;

public class Constants {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//	public static final String DB_CONNECT_STR = "jdbc:MySQL://127.0.0.1:3306/mytest?user=root&password=123456";
//	public static final String DB_SCHEMA_PATTERN = "mytest";

//	public static final String DB_CONNECT_STR = "jdbc:MySQL://127.0.0.1:3306/license?user=root&password=123456";
	public static final String DB_CONNECT_STR_PATTERN = "jdbc:MySQL://%s:%s/%s?user=%s&password=%s";
//	public static final String DB_SCHEMA_PATTERN = "license";
//	public static final String DB_TABLE_PATTERN = "license%";

	public static final String YES = "YES";
	public static final String NO = "NO";

//	public static final String DB_MYSQL_CONNECT_STR = "jdbc:MySQL://127.0.0.1:3306/information_schema?user=root&password=123456";
	public static final String DB_MYSQL_CONNECT_STR_PATTERN = "jdbc:MySQL://%s:%s/information_schema?user=%s&password=%s";

	public static final String ROOT_PATH = "ROOT_PATH";
	public static final String SQL_PATH = "SQL_PATH";
	public static final String HTML_PATH = "HTML_PATH";
	public static final String MENU_PATH = "MENU_PATH";
	public static final String UI_MODEL_PATH = "UI_MODEL_PATH";

	public static final String ENTITY_NAME = "ENTITY_NAME";
	public static final String DAO_NAME = "DAO_NAME";
	public static final String SERVICE_NAME = "SERVICE_NAME";
	public static final String SERVICE_IMPL_NAME = "SERVICE_IMPL_NAME";
	public static final String ACTION_NAME = "ACTION_NAME";

	public static final String IP = "IP";
	public static final String PORT = "PORT";
	public static final String USER_NAME = "USER_NAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String SCHEMA = "SCHEMA";

	public static final String TABLE_PATTERN = "TABLE_PATTERN";
}