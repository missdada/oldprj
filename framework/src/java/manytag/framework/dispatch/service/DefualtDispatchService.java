package manytag.framework.dispatch.service;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefualtDispatchService implements DispatchService {
	public void handle(HttpContext httpContext) {
		try {
			sendHeader(httpContext);
			String method = httpContext.getRequest().getMethod();
			if (!"POST".equals(method) && !"GET".equals(method)) {
				httpContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void sendHeader(HttpContext httpContext) throws IOException {
		HttpServletRequest request = httpContext.getRequest();
		HttpServletResponse response = httpContext.getResponse();
		ServletConfig config = httpContext.getConfig();

		response.setContentType("text/plain");
		String param = config.getInitParameter("crossDomain");
		if (param != null) {
			param = param.toLowerCase();
			if (param.equals("true")) {
				String origin = request.getHeader("Origin");
				if (origin != null && !origin.equals("null")) {
					response.setHeader("Access-Control-Allow-Origin", origin);
					response.setHeader("Access-Control-Allow-Credentials", "true");
				} else {
					response.setHeader("Access-Control-Allow-Origin", "*");
				}
			}
		}
	}
}