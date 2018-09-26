package manytag.framework.dispatch.base.response.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.handler.ResponseHandler;
import manytag.framework.dispatch.base.response.impl.JsonListResponse;
import manytag.framework.dispatch.base.response.impl.JsonObjResponse;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

public class JsonResponseHandler implements ResponseHandler {
	private ObjectMapper objMapper = new ObjectMapper();

	public void handleResponse(ServerResponse result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jsonStr = "";
		if (result instanceof JsonObjResponse) {
			JsonObjResponse jr = (JsonObjResponse) result;
			jsonStr = objMapper.writeValueAsString(jr.getJsonObj());
		} else if (result instanceof JsonResponse) {
			JsonResponse jr = (JsonResponse) result;
			jsonStr = objMapper.writeValueAsString(jr.get());
		} else if (result instanceof JsonListResponse) {
			JsonListResponse jr = (JsonListResponse) result;
			jsonStr = objMapper.writeValueAsString(jr.getJsonList());
		}

		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().write(jsonStr);
		response.getWriter().flush();
	}
}