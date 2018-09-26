package manytag.framework.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static String obj2Str(Object obj) {
		String jsonStr = null;
		try {
			ObjectMapper objMapper = new ObjectMapper();
			jsonStr = objMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public static <T> T str2Obj(String json, Class<T> classOfT) {
		T obj = null;
		try {
			ObjectMapper objMapper = new ObjectMapper();
			obj = objMapper.readValue(json, classOfT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}