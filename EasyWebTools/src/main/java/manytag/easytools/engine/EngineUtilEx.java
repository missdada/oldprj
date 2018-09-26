package manytag.easytools.engine;

import java.util.Map;

import manytag.easytools.data.DBStructInfo;
import manytag.easytools.data.DBTableInfo;
import manytag.easytools.util.FileUtil;
import manytag.easytools.util.FreeMarkerUtil;

public class EngineUtilEx extends EngineUtil {

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		String template = getResource(templatePath);
		FileUtil oFileUtil = new FileUtil();
		Map<String, DBTableInfo> tables = dbStruct.getTables();

		String fileNameMid = "";
		String fileName = filePrefix + fileNameMid + fileSuffix;
		String destFileName = createDirectory(this.filePath) + fileName;

		parameters.put("tables", tables);
		String mergeResult = FreeMarkerUtil.merge(template, parameters);
		oFileUtil.writeFile(mergeResult, destFileName);
	}
}