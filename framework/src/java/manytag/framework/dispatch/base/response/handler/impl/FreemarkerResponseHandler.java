package manytag.framework.dispatch.base.response.handler.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.handler.ResponseHandler;
import manytag.framework.dispatch.base.response.impl.FreemarkerResponse;

public class FreemarkerResponseHandler implements ResponseHandler {
	public void handleResponse(ServerResponse result, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String templatePath;
		if (result instanceof FreemarkerResponse) {
			FreemarkerResponse fr = (FreemarkerResponse) result;

			if (fr.getTemplatePath() != null && !"".equals(fr.getTemplatePath())) {
				templatePath = fr.getTemplatePath() + ".ftl";
			} else {
				templatePath = "pub/error.ftl";
			}
			if (result.getMessage() != null && "".equals(result.getMessage().getMessageId())) {
				request.setAttribute(Constants.STR_MESSAGE, result.getMessage());
			}

			request.getRequestDispatcher(templatePath).forward(request, response);
		}
	}
}