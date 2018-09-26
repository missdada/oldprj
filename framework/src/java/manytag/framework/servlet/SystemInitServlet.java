package manytag.framework.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import manytag.framework.dispatch.base.ApplicationContext;

public class SystemInitServlet extends GenericServlet {
	private static final long serialVersionUID = 6927904928417189821L;

	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ApplicationContext.init(wac);

		Enumeration<String> initParaNames = config.getInitParameterNames();
		while (initParaNames.hasMoreElements()) {
			String key = initParaNames.nextElement();
			String value = config.getInitParameter(key);
			ApplicationContext.setSystemInitProperty(key, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

	}
}