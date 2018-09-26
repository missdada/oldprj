package manytag.framework.dispatch.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.context.WebApplicationContext;

public class ApplicationContext {
	private static WebApplicationContext appContext;

	private static Map<String, Object> globalBuffer;

	private static Properties properties;

	public static void init(WebApplicationContext wac) {
		appContext = wac;
		globalBuffer = new HashMap<String, Object>();
		properties = new Properties();
	}

	public static <T> T getBean(String beanname, Class<T> c) {
		return (T) appContext.getBean(beanname, c);
	}

	public static <T> T getBean(Class<T> c) {
		return appContext.getBean(c);
	}

	public static Object getBean(String beanname) {
		return appContext.getBean(beanname);
	}

	/**
	 * Description:在全局内存中，根据key获取内存对象。<br>
	 * 
	 * @param key ：key
	 * @return key所对应的对象。如果不存在，则返回null
	 */
	public static Object getGlobalVariable(String key) {
		return globalBuffer.get(key);
	}

	/**
	 * Description:向全局内存中，设置内存对象<br>
	 * 
	 * @param key ：key
	 * @param value ：value
	 * @return 之前的旧值。若之前没有设定过，则返回null
	 */
	public static Object setGlobalVariable(String key, Object value) {
		return globalBuffer.put(key, value);
	}

	public static String getSystemInitProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getSystemInitProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static Object setSystemInitProperty(String key, String value) {
		return properties.setProperty(key, value);
	}
}