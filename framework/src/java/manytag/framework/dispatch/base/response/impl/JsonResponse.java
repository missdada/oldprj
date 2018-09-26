package manytag.framework.dispatch.base.response.impl;

import java.util.Map;

import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.ServerResponse;

public class JsonResponse extends ServerResponse {
	public JsonResponse() {
		super(ServerResponse.RESULT_TYPE_JSON);
	}

	public JsonResponse(Map<String, Object> map) {
		super(ServerResponse.RESULT_TYPE_JSON);
		map.putAll(map);
	}

	public JsonResponse(Message message) {
		setMessage(message);
	}

	public JsonResponse(String msgType, String msgId, String msg) {
		setMessage(new Message(msgType, msgId, msg));
	}

	public JsonResponse(String msgType, String msgId) {
		this(msgType, msgId, null);
	}

	public void setMessage(Message message) {
		super.setMessage(message);
		put(Constants.STR_MESSAGE, message);
	}
}