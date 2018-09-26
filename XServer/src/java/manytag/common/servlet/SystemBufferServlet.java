package manytag.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SystemBufferServlet extends HttpServlet {
	private static final long serialVersionUID = 4878969914384756692L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initSystemBuffer();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		super.service(arg0, arg1);
	}

	protected void initSystemBuffer() {

	}
}