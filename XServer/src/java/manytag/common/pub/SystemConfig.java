package manytag.common.pub;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;
import org.w3c.dom.Node;

import manytag.framework.util.DataXmlDoc;

public class SystemConfig implements InitializingBean, ServletContextAware {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 配置文件
	 */
	private String configFile;

	private Map<String, String> configBuffer;

	private static final String WEB_TEMPLATE_PATH = "web-template-path";

	public SystemConfig() {
		configBuffer = new HashMap<String, String>();
	}

	public String getConfig(String id) {
		return configBuffer.get(id);
	}

	public void setServletContext(ServletContext arg0) {
		if (log.isDebugEnabled()) {
			log.debug("load " + configFile);
		}

		InputStream stream = this.getClass().getResourceAsStream(configFile);
		init(stream);
	}

	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public void afterPropertiesSet() throws Exception {

	}

	public void init(InputStream stream) {
		try {
			DataXmlDoc doc = new DataXmlDoc();
			doc.Load(stream);

			String webTemplatePath = getNodeValue(doc.getNode("//web-template-path"));

			configBuffer.put(WEB_TEMPLATE_PATH, webTemplatePath);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
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
}