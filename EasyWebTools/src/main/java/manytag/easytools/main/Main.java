package manytag.easytools.main;

import manytag.easytools.data.DBStructInfo;
import manytag.easytools.db.DBTableStruct;
import manytag.easytools.db.MySqlDBUtil;
import manytag.easytools.engine.CreateAction;
import manytag.easytools.engine.CreateDAO;
import manytag.easytools.engine.CreateHtml;
import manytag.easytools.engine.CreateMenuData;
import manytag.easytools.engine.CreateModel;
import manytag.easytools.engine.CreateService;
import manytag.easytools.pub.Constants;
import manytag.easytools.pub.SystemConfig;

public class Main {
	public static void main(String[] args) {
		SystemConfig sysConfig = SystemConfig.getInstance();

		String outputPath = sysConfig.getConfig(Constants.ROOT_PATH);
		String sqlMapperPath = sysConfig.getConfig(Constants.SQL_PATH);
		String htmlPath = sysConfig.getConfig(Constants.HTML_PATH);
		String menuDataPath = sysConfig.getConfig(Constants.MENU_PATH);
		String uiModelDataPath = sysConfig.getConfig(Constants.UI_MODEL_PATH);
		String entityPackage = sysConfig.getConfig(Constants.ENTITY_NAME);
		String daoPackage = sysConfig.getConfig(Constants.DAO_NAME);
		String servicePackage = sysConfig.getConfig(Constants.SERVICE_NAME);
		String serviceImplPackage = sysConfig.getConfig(Constants.SERVICE_IMPL_NAME);
		String actionPackage = sysConfig.getConfig(Constants.ACTION_NAME);
		String schema = sysConfig.getConfig(Constants.SCHEMA);
		String tablePattern = sysConfig.getConfig(Constants.TABLE_PATTERN);

		DBTableStruct oDBTableStruct = new DBTableStruct(schema, tablePattern);
		DBStructInfo oDBStructInfo = oDBTableStruct.getDBStructInfo();
		// 只对mysql数据库有效
		MySqlDBUtil.setTablesRemark(schema, oDBStructInfo);

		// 生成 sql mapper配置文件和Entity类
		CreateDAO oCreateDAO = new CreateDAO();
		oCreateDAO.setOutputPath(outputPath);
		oCreateDAO.setEntityPackage(entityPackage);
		oCreateDAO.setDaoPackage(daoPackage);
		oCreateDAO.setSqlMapperPath(sqlMapperPath);
		oCreateDAO.execute(oDBStructInfo);

		// 生成UI模型数据
		CreateModel oCreateModel = new CreateModel();
		oCreateModel.setOutputPath(outputPath);
		oCreateModel.setUiModelDataPath(uiModelDataPath);
		oCreateModel.execute(oDBStructInfo);

		// 生成服务接口和服务实现类
		CreateService oCreateService = new CreateService();
		oCreateService.setOutputPath(outputPath);
		oCreateService.setEntityPackage(entityPackage);
		oCreateService.setDaoPackage(daoPackage);
		oCreateService.setServicePackage(servicePackage);
		oCreateService.setServiceImplPackage(serviceImplPackage);
		oCreateService.execute(oDBStructInfo);

		// 生成Action类
		CreateAction oCreateAction = new CreateAction();
		oCreateAction.setOutputPath(outputPath);
		oCreateAction.setEntityPackage(entityPackage);
		oCreateAction.setServicePackage(servicePackage);
		oCreateAction.setActionPackage(actionPackage);
		oCreateAction.execute(oDBStructInfo);

		// 生成UI模型数据
		CreateHtml oCreateHtml = new CreateHtml();
		oCreateHtml.setOutputPath(outputPath);
		oCreateHtml.setHtmlPath(htmlPath);
		oCreateHtml.execute(oDBStructInfo);

		// 生成Menu脚本
		CreateMenuData oCreateMenuData = new CreateMenuData();
		oCreateMenuData.setOutputPath(outputPath);
		oCreateMenuData.setMenuDataPath(menuDataPath);
		oCreateMenuData.execute(oDBStructInfo);

		System.out.println("代码生成完成！输出路径：" + outputPath);
	}
}