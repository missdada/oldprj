package manytag.easytools.engine;

import manytag.easytools.data.DBStructInfo;

public class CreateDAO extends BaseEngine {
	public CreateDAO() {

	}

	@Override
	protected void doExecute(DBStructInfo dbStruct) {
//		createBaseEntity();
		createEntity(dbStruct);
		createSearchEntity(dbStruct);
		createMapperConfig(dbStruct);
		createDao(dbStruct);
	}

	/*
	private void createBaseEntity() {
		String baseEntityTemplate = getResource("/template/BaseEntity.ftl");
		FileUtil oFileUtil = new FileUtil();
		String destFileName = createDirectory(this.getOutputEntityPath()) + "BaseEntity.java";
		oFileUtil.copyFile(baseEntityTemplate, destFileName);
	}
	*/

	private void createEntity(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Entity.ftl");
		oEngineUtil.setFileSuffix("Entity.java");
		oEngineUtil.setFilePath(this.getOutputEntityPath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.execute(dbStruct);
	}

	private void createSearchEntity(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/SearchEntity.ftl");
		oEngineUtil.setFileSuffix("SearchEntity.java");
		oEngineUtil.setFilePath(this.getOutputEntityPath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.execute(dbStruct);
	}

	private void createMapperConfig(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/MyBatisMapper.ftl");
		oEngineUtil.setFileSuffix("_Mapper.xml");
		oEngineUtil.setFilePath(this.getOutputSqlMapperPath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.getParameters().put("daoPackageInfo", this.getDaoPackage());
		oEngineUtil.execute(dbStruct);
	}

	private void createDao(DBStructInfo dbStruct) {
		EngineUtil oEngineUtil = createEngineUtil();
		oEngineUtil.setTemplatePath("/template/Dao.ftl");
		oEngineUtil.setFileSuffix("Dao.java");
		oEngineUtil.setFilePath(this.getOutputDaoPath());
		oEngineUtil.getParameters().put("entityPackageInfo", this.getEntityPackage());
		oEngineUtil.getParameters().put("daoPackageInfo", this.getDaoPackage());
		oEngineUtil.execute(dbStruct);
	}
}