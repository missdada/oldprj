package manytag.easytools.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColumnExtendInfoParser {
	public static void parse(String str, Object extendInfo) {
		Map<String, Object> properties = patternParse(str);
		BeanUtils.restoreProp(extendInfo, properties);
	}

	private static Map<String, Object> patternParse(String content) {
		Pattern pattern = Pattern.compile("(?<key>[^=,;\\s]+)\\s*=\\s*(?<value>[^;,]+)\\s*[;,]?");
		Matcher matcher = pattern.matcher(content);

		Map<String, Object> properties = new HashMap<String, Object>();
		while (matcher.find()) {
			String key = matcher.group("key");
			String value = matcher.group("value");

			properties.put(key, value);
//			System.out.println(key + "=" + value);
		}

		return properties;
	}
}