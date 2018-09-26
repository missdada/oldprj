package manytag.easytools.pub;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

import manytag.easytools.util.DataXmlDoc;

public class SystemConfig {
	private Map<String, String> configBuffer;

	private static SystemConfig instance = new SystemConfig();

	public static SystemConfig getInstance() {
		return instance;
	}

	protected SystemConfig() {
		configBuffer = new HashMap<String, String>();

		initConfig();
	}

	public String getConfig(String id) {
		return configBuffer.get(id);
	}

	public int getConfigInt(String id) {
		String value = configBuffer.get(id);
		if (value != null && !"".equals(value)) {
			return Integer.parseInt(value);
		}

		return -1;
	}

	public void initConfig() {
		InputStream stream = this.getClass().getResourceAsStream("/system-config.xml");
		init(stream);
	}

	public void afterPropertiesSet() throws Exception {

	}

	public void init(InputStream stream) {
		try {
			DataXmlDoc doc = new DataXmlDoc();
			doc.Load(stream);

			setBufferValueFromConfig(doc, Constants.ROOT_PATH, "//root-path");
			setBufferValueFromConfig(doc, Constants.SQL_PATH, "//sql-path");
			setBufferValueFromConfig(doc, Constants.HTML_PATH, "//html-path");
			setBufferValueFromConfig(doc, Constants.MENU_PATH, "//menu-path");
			setBufferValueFromConfig(doc, Constants.UI_MODEL_PATH, "//ui-model-path");

			setBufferValueFromConfig(doc, Constants.ENTITY_NAME, "//entity-name");
			setBufferValueFromConfig(doc, Constants.DAO_NAME, "//dao-name");
			setBufferValueFromConfig(doc, Constants.SERVICE_NAME, "//service-name");
			setBufferValueFromConfig(doc, Constants.SERVICE_IMPL_NAME, "//service-impl-name");
			setBufferValueFromConfig(doc, Constants.ACTION_NAME, "//action-name");

			setBufferValueFromConfig(doc, Constants.IP, "//connection/ip");
			setBufferValueFromConfig(doc, Constants.PORT, "//connection/port");
			setBufferValueFromConfig(doc, Constants.USER_NAME, "//connection/user-name");
			setBufferValueFromConfig(doc, Constants.PASSWORD, "//connection/password");
			setBufferValueFromConfig(doc, Constants.SCHEMA, "//connection/schema");

			setBufferValueFromConfig(doc, Constants.TABLE_PATTERN, "//table-pattern");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setBufferValueFromConfig(DataXmlDoc doc, String key, String xPath) {
		String value = getNodeValue(doc.getNode(xPath));
		configBuffer.put(key, value);
	}

	protected String getNodeValue(Node oNode) {
		String sResultString = "";

		if (oNode != null) {
			if (oNode.getChildNodes().getLength() > 1) {
				sResultString = oNode.getFirstChild().getTextContent().trim();
			} else {
				sResultString = oNode.getTextContent().trim();
			}
		}

		return sResultString;
	}

	public void setBufferValue(String key, String value) {
		configBuffer.put(key, value);
	}

	public String getDbConnectStr() {
		String ip = getConfig(Constants.IP);
		String port = getConfig(Constants.PORT);
		String userName = getConfig(Constants.USER_NAME);
		String password = getConfig(Constants.PASSWORD);
		String schema = getConfig(Constants.SCHEMA);
		String dbConnectStr = String.format(Constants.DB_CONNECT_STR_PATTERN, ip, port, schema, userName, password);
		return dbConnectStr;
	}

	public String getDbMySqlSchemaInfoStr() {
		String ip = getConfig(Constants.IP);
		String port = getConfig(Constants.PORT);
		String userName = getConfig(Constants.USER_NAME);
		String password = getConfig(Constants.PASSWORD);
		String dbConnectStr = String.format(Constants.DB_MYSQL_CONNECT_STR_PATTERN, ip, port, userName, password);
		return dbConnectStr;
	}
}