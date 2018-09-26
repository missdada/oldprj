package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class CreateModel extends BaseEngine {
	public CreateModel() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createUIModelData(dbStruct);
		createUIModelSkin(dbStruct);
	}

	private void createUIModelData(DBStructInfo dbStruct) {
		createUIModelEdit(dbStruct);
		createUIModelSearch(dbStruct);
		createUIModelReference(dbStruct);
	}

	private void createUIModelSkin(DBStructInfo dbStruct) {

	}

	private void createUIModelEdit(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/UIModelDataEdit.ftl");
		oEngineUtil.setFileSuffix("_Edit.xml");
		oEngineUtil.setFilePath(this.getOutputUiModelDataPath());
		oEngineUtil.setName2Hump(false);
		oEngineUtil.execute(dbStruct);
	}

	private void createUIModelSearch(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/UIModelDataSearch.ftl");
		oEngineUtil.setFileSuffix("_Search.xml");
		oEngineUtil.setFilePath(this.getOutputUiModelDataPath());
		oEngineUtil.setName2Hump(false);
		oEngineUtil.execute(dbStruct);
	}

	private void createUIModelReference(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/UIModelDataReference.ftl");
		oEngineUtil.setFileSuffix("_Reference.xml");
		oEngineUtil.setFilePath(this.getOutputUiModelDataPath());
		oEngineUtil.setName2Hump(false);
		oEngineUtil.execute(dbStruct);
	}
}