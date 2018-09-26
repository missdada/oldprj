package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class CreateMenuData extends BaseEngine {
	public CreateMenuData() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createMenuData(dbStruct);
	}

	private void createMenuData(DBStructInfo dbStruct) {
		EngineUtilEx oEngineUtil = createEngineUtilEx();
		oEngineUtil.setTemplatePath("/template/MenuData.ftl");
		oEngineUtil.setFileSuffix("menuData.txt");
		oEngineUtil.setFilePath(this.getOutputMenuDataPath());
		oEngineUtil.execute(dbStruct);
	}
}