package manytag.framework.dispatch.base.response.handler.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.handler.ResponseHandler;
import manytag.framework.dispatch.base.response.impl.JsonRedirectResponse;

public class JsonRedirectResponseHandler implements ResponseHandler {
	public void handleResponse(ServerResponse result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (result instanceof JsonRedirectResponse) {
			JsonRedirectResponse jr = (JsonRedirectResponse) result;
			if (result.getMessage() != null && "".equals(result.getMessage().getMessageId())) {
				request.setAttribute(Constants.STR_MESSAGE, result.getMessage());
			}
			String url = jr.getUrlPath();
			RequestDispatcher requestDispatcher = jr.getRequestDispatcher();
			if (url != null && !"".equals(url)) {
				response.sendRedirect(url);
			} else if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			}
		}
	}
}