package manytag.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IFileUploadService {
	public Map<String, Object> uploadfile(HttpServletRequest request);
}