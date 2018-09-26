package manytag.framework.dispatch.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manytag.framework.dispatch.service.DefualtDispatchService;
import manytag.framework.dispatch.service.DispatchService;
import manytag.framework.dispatch.service.HttpContext;

public class WebRequestDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 5293689197643115650L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		DispatchService service = null;
		try {
			String handlername = this.getServletConfig().getInitParameter("service-name");
			if (handlername == null || "".equals(handlername)) {
				service = new DefualtDispatchService();
			} else {
				Class<?> handle = Class.forName(handlername);
				if (DispatchService.class.isAssignableFrom(handle)) {
					service = (DispatchService) handle.newInstance();
				}
			}

			service.handle(new HttpContext(request, response, this.getServletConfig(), this.getServletContext()));
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}