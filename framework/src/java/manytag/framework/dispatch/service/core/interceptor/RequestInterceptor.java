package manytag.framework.dispatch.service.core.interceptor;

import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.response.ServerResponse;

public interface RequestInterceptor {
	void init();

	void destroy();

	ServerResponse intercept(BaseAction invocation, String method) throws Exception;

	ServerResponse intercept(String url, BaseAction invocation, String method) throws Exception;
}