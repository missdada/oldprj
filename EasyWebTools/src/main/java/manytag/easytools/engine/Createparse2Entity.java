package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class Createparse2Entity extends BaseEngine {
	public Createparse2Entity() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createparse2Entity(dbStruct);
	}

	private void createparse2Entity(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Pase2Entity.ftl");
		oEngineUtil.setFileSuffix("Entity.txt");
		oEngineUtil.setFilePath(this.getParse2EntityPath());
		oEngineUtil.execute(dbStruct);
	}
}