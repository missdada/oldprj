package manytag.framework.dispatch.base.response.impl;

import java.util.ArrayList;
import java.util.List;

import manytag.framework.dispatch.base.response.ServerResponse;

public class JsonListResponse extends ServerResponse {
	private List<Object> jsonList = new ArrayList<Object>();

	public JsonListResponse() {
		super(ServerResponse.RESULT_TYPE_JSON);
	}

	public JsonListResponse(List<Object> jsonList) {
		super(ServerResponse.RESULT_TYPE_JSON);
		this.jsonList = jsonList;
	}

	public List<Object> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<Object> jsonList) {
		this.jsonList = jsonList;
	}

	public JsonListResponse putValue(Object o) {
		jsonList.add(o);
		return this;
	}

	public JsonListResponse putValue(List<Object> l) {
		jsonList.add(l);
		return this;
	}
}