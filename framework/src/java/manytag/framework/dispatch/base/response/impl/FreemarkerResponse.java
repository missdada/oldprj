package manytag.framework.dispatch.base.response.impl;

import manytag.framework.dispatch.base.response.ServerResponse;

public class FreemarkerResponse extends ServerResponse {
	private String templatePath;

	public FreemarkerResponse() {
		super(ServerResponse.RESULT_TYPE_FREEMARKER);
	}

	public FreemarkerResponse(String templatePath) {
		super(ServerResponse.RESULT_TYPE_FREEMARKER);
		this.templatePath = templatePath;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
}