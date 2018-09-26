package manytag.framework.dispatch.base.response.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.base.response.ServerResponse;

public interface ResponseHandler {
	public void handleResponse(ServerResponse result, HttpServletRequest request, HttpServletResponse response) throws Exception;
}