package manytag.easytools.engine;

import java.util.Map;

import manytag.easytools.data.DBStructInfo;
import manytag.easytools.data.DBTableInfo;

public class CreateHtml extends BaseEngine {
	public CreateHtml() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createHtml(dbStruct);
		createHtmlDetail(dbStruct);
		createHtmlView(dbStruct);
		createHtmlIndex(dbStruct);
		createHtmlTree(dbStruct);
	}

	private void createHtml(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Html.ftl");
		oEngineUtil.setFileSuffix(".html");
		oEngineUtil.setFilePath(this.getOutputHtmlPath());
		oEngineUtil.execute(dbStruct);
	}

	private void createHtmlDetail(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/HtmlDetail.ftl");
		oEngineUtil.setFileSuffix("_Detail.html");
		oEngineUtil.setFilePath(this.getOutputHtmlPath());
		oEngineUtil.execute(dbStruct);
	}

	private void createHtmlView(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/HtmlView.ftl");
		oEngineUtil.setFileSuffix("_View.html");
		oEngineUtil.setFilePath(this.getOutputHtmlPath());
		oEngineUtil.execute(dbStruct);
	}

	private void createHtmlIndex(DBStructInfo dbStruct) {
		EngineUtilEx oEngineUtil = createEngineUtilEx();
		oEngineUtil.setTemplatePath("/template/HtmlIndex.ftl");
		oEngineUtil.setFileSuffix("indexX.html");
		oEngineUtil.setFilePath(this.getOutputHtmlPath());
		oEngineUtil.execute(dbStruct);
	}

	private void createHtmlTree(DBStructInfo dbStruct) {
		DBStructInfo dbStructNew = getExtDBStruct(dbStruct, "tree");
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/HtmlTree.ftl");
		oEngineUtil.setFileSuffix(".html");
		oEngineUtil.setFilePath(this.getOutputHtmlPath());
		oEngineUtil.execute(dbStructNew);
	}

	private DBStructInfo getExtDBStruct(DBStructInfo dbStruct, String targetRealType) {
		DBStructInfo dbStructNew = new DBStructInfo();
		Map<String, DBTableInfo> tables = dbStruct.getTables();
		for (String tableName : tables.keySet()) {
			DBTableInfo oDBTableInfo = tables.get(tableName);

			String realType = oDBTableInfo.getExtendInfo().getT();
			if (realType != null && !"".equals(realType)) {
				if (realType.equalsIgnoreCase(targetRealType)) {
					dbStructNew.getTables().put(tableName, oDBTableInfo);
				}
			}
		}
		return dbStructNew;
	}
}