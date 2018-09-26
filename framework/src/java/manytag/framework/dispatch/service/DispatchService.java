package manytag.framework.dispatch.service;

public interface DispatchService {
	public void handle(HttpContext httpContext) throws Exception;
}