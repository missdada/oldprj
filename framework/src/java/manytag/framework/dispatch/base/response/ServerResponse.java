package manytag.framework.dispatch.base.response;

import java.util.HashMap;
import java.util.Map;

import manytag.framework.dispatch.base.Message;

public class ServerResponse {
	public static final String RESULT_TYPE_JSON = "json";
	public static final String RESULT_TYPE_JSONP = "jsonp";
	public static final String RESULT_TYPE_REDIRECT = "redirect";

	public static final String RESULT_TYPE_FREEMARKER = "freemarker";

	private Message message;
	private String type;
	private Map<String, Object> map = new HashMap<String, Object>();
	private Object jsonObj = null;

	public void put(String id, Object v) {
		map.put(id, v);
	}

	public void put(Map<String, Object> map) {
		this.map.putAll(map);
	}

	public Map<String, Object> get() {
		return map;
	}

	public Object getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(Object jsonObj) {
		this.jsonObj = jsonObj;
	}

	public ServerResponse() {
		type = RESULT_TYPE_FREEMARKER;
	}

	public ServerResponse(String type) {
		this.type = type;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}