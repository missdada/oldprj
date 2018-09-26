package manytag.framework.dispatch.service.core.interceptor;

import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.response.ServerResponse;

public class BaseInterceptor implements RequestInterceptor {
	public void init() {

	}

	public void destroy() {

	}

	@Override
	public ServerResponse intercept(BaseAction invocation, String method) throws Exception {
		return null;
	}

	@Override
	public ServerResponse intercept(String url, BaseAction invocation, String method) throws Exception {
		return null;
	}
}