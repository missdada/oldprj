package manytag.framework.dispatch.service.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.handler.ResponseHandler;
import manytag.framework.dispatch.base.response.handler.impl.FreemarkerResponseHandler;
import manytag.framework.dispatch.base.response.handler.impl.JsonRedirectResponseHandler;
import manytag.framework.dispatch.base.response.handler.impl.JsonResponseHandler;
import manytag.framework.dispatch.service.DispatchService;
import manytag.framework.dispatch.service.HttpContext;
import manytag.framework.dispatch.service.core.interceptor.RequestInterceptor;

public class HttpRequestHandler implements DispatchService {
	private List<RequestInterceptor> requestInterceptors = new ArrayList<RequestInterceptor>();

	public void setRequestInterceptors(List<RequestInterceptor> requestInterceptors) {
		this.requestInterceptors = requestInterceptors;
	}

	public void handle(HttpContext httpContext) throws Exception {
		HttpServletRequest request = httpContext.getRequest();
		HttpServletResponse response = httpContext.getResponse();

		String method = request.getParameter("_method");
		String servletName = request.getServletPath();

		if (servletName.startsWith("/")) {
			servletName = servletName.substring(1);
		}
		if (servletName.endsWith(".do")) {
			servletName = servletName.replaceAll(".do", "");
		}
		BaseAction action = (BaseAction) ApplicationContext.getBean(servletName);
		action.setHttpContext(httpContext);
		ServerResponse result = null;
		if (requestInterceptors.size() > 0) {
			for (RequestInterceptor invoker : requestInterceptors) {
				invoker.init();
				try {
					result = invoker.intercept(action, method);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					invoker.destroy();
				}
			}
		} else {
			result = action.execute(method);
		}

		// 没有设置JsonResponse对象时，result为null，不做响应分发处理
		if (result == null) {
			return;
		}

		ResponseHandler resultHandler = null;
		if (ServerResponse.RESULT_TYPE_FREEMARKER.equals(result.getType())) {
			resultHandler = ApplicationContext.getBean(Constants.RESPONSE_FREEMARKER_HANDLER, FreemarkerResponseHandler.class);
		} else if (ServerResponse.RESULT_TYPE_JSON.equals(result.getType())) {
			resultHandler = ApplicationContext.getBean(Constants.RESPONSE_JSON_HANDLER, JsonResponseHandler.class);
		} else if (ServerResponse.RESULT_TYPE_REDIRECT.equals(result.getType())) {
			resultHandler = ApplicationContext.getBean(Constants.RESPONSE_JSON_REDIRECT_HANDLER, JsonRedirectResponseHandler.class);
		}

		resultHandler.handleResponse(result, request, response);
	}
}