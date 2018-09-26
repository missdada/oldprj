package manytag.framework.dispatch.service.spring;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import manytag.framework.dispatch.service.DispatchService;
import manytag.framework.dispatch.service.HttpContext;

public class SpringDispatchService implements DispatchService {
	public void handle(HttpContext httpContext) {
		try {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(httpContext.getApplication());
			Object handler = wac.getBean("request-handler");
			if (handler != null && handler instanceof DispatchService) {
				if (handler instanceof DispatchService) {
					((DispatchService) handler).handle(httpContext);
				} else {
					System.err.println(handler.getClass().getName() + "request-handler is not DispatchService instance.");
				}
			} else {
				System.err.println("no request-handler in spring config.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}