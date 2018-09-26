package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class CreateService extends BaseEngine {
	public CreateService() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
		createService(dbStruct);
		createServiceImpl(dbStruct);
	}

	private void createService(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Service.ftl");
		oEngineUtil.setFilePrefix("I");
		oEngineUtil.setFileSuffix("Service.java");
		oEngineUtil.setFilePath(this.getOutputServicePath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.getParameters().put("servicePackageInfo", this.getServicePackage());
		oEngineUtil.execute(dbStruct);
	}

	private void createServiceImpl(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/ServiceImpl.ftl");
		oEngineUtil.setFileSuffix("Service.java");
		oEngineUtil.setFilePath(this.getOutputServiceImplPath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.getParameters().put("daoPackageInfo", this.getDaoPackage());
		oEngineUtil.getParameters().put("servicePackageInfo", this.getServicePackage());
		oEngineUtil.getParameters().put("serviceImplPackageInfo", this.getServiceImplPackage());
		oEngineUtil.execute(dbStruct);
	}
}