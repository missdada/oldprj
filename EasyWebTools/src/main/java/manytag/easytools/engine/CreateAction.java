package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class CreateAction extends BaseEngine {
	public CreateAction() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createAction(dbStruct);
	}

	private void createAction(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Action.ftl");
		oEngineUtil.setFileSuffix("Action.java");
		oEngineUtil.setFilePath(this.getOutputActionPath());
		oEngineUtil.getParameters().put("actionPackageInfo", this.getActionPackage());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.getParameters().put("servicePackageInfo", this.getServicePackage());
		oEngineUtil.execute(dbStruct);
	}
}