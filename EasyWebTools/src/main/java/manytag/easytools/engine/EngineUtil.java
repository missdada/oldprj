package manytag.easytools.engine;

import java.util.HashMap;
import java.util.Map;

import manytag.easytools.data.DBStructInfo;
import manytag.easytools.data.DBTableInfo;
import manytag.easytools.util.FileUtil;
import manytag.easytools.util.FreeMarkerUtil;

public class EngineUtil extends BaseEngine {
	protected String templatePath;
	protected String filePath;
	protected String fileSuffix;
	protected String filePrefix;
	protected Map<String, Object> parameters;
	private boolean name2Hump;

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public boolean isName2Hump() {
		return name2Hump;
	}

	public void setName2Hump(boolean name2Hump) {
		this.name2Hump = name2Hump;
	}

	public EngineUtil() {
		fileSuffix = "";
		filePrefix = "";

		parameters = new HashMap<String, Object>();
		name2Hump = true;
	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		String template = getResource(this.templatePath);
		FileUtil oFileUtil = new FileUtil();
		Map<String, DBTableInfo> tables = dbStruct.getTables();
		for (String tableName : tables.keySet()) {
			String fileNameMid = isName2Hump() ? name2HumpFormat(tableName, true) : tableName;
			String fileName = filePrefix + fileNameMid + fileSuffix;
			String destFileName = createDirectory(this.filePath) + fileName;

			DBTableInfo oDBTableInfo = tables.get(tableName);

			parameters.put("oDBTableInfo", oDBTableInfo);
			String mergeResult = FreeMarkerUtil.merge(template, parameters);
			oFileUtil.writeFile(mergeResult, destFileName);
		}
	}
}