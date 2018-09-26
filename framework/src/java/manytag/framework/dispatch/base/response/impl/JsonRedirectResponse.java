package manytag.framework.dispatch.base.response.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.base.response.ServerResponse;

public class JsonRedirectResponse extends ServerResponse {
	private String urlPath;
	private RequestDispatcher requestDispatcher;

	public JsonRedirectResponse() {
		super(RESULT_TYPE_REDIRECT);
	}

	public JsonRedirectResponse(String url, RequestDispatcher rd) {
		super(RESULT_TYPE_REDIRECT);
		urlPath = url;
		requestDispatcher = rd;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public RequestDispatcher getRequestDispatcher() {
		return requestDispatcher;
	}

	public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}

	public void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestDispatcher.forward(request, response);
	}
}