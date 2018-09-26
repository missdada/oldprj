package manytag.easytools.engine;

import java.net.URL;

import manytag.easytools.data.DBStructInfo;
import manytag.easytools.util.FileUtil;

public abstract class BaseEngine {
	String entityPackage;
	String daoPackage;
	String sqlMapperPath;
	String uiModelDataPath;
	String outputPath;
	String servicePackage;
	String serviceImplPackage;
	String actionPackage;
	String htmlPath;
	String menuDataPath;
	String parse2EntityPath;

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getSqlMapperPath() {
		return sqlMapperPath;
	}

	public void setSqlMapperPath(String sqlMapperPath) {
		this.sqlMapperPath = sqlMapperPath;
	}

	public String getUiModelDataPath() {
		return uiModelDataPath;
	}

	public void setUiModelDataPath(String uiModelDataPath) {
		this.uiModelDataPath = uiModelDataPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getActionPackage() {
		return actionPackage;
	}

	public void setActionPackage(String actionPackage) {
		this.actionPackage = actionPackage;
	}

	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getMenuDataPath() {
		return menuDataPath;
	}

	public void setMenuDataPath(String menuDataPath) {
		this.menuDataPath = menuDataPath;
	}

	public String getParse2EntityPath() {
		return this.outputPath + parse2EntityPath + "/";
	}

	public void setParse2EntityPath(String parse2EntityPath) {
		this.parse2EntityPath = parse2EntityPath;
	}

	public void execute(DBStructInfo dbStruct) {
		checkOutputPath();
		doExecute(dbStruct);
	}

	protected abstract void doExecute(DBStructInfo dbStruct);

	protected boolean checkOutputPath() {
		boolean bRet = false;
		String outputPath = getOutputPath();
		bRet = outputPath.equals(createDirectory(outputPath));
		return bRet;
	}

	protected String createDirectory(String dir) {
		boolean bRet = true;
		FileUtil oFileUtil = new FileUtil();
		if (!oFileUtil.isExist(dir)) {
			bRet = oFileUtil.createDirectory(dir);
		}
		if (bRet) {
			return dir;
		}

		return null;
	}

	protected String getOutputEntityPath() {
		String path = this.outputPath + this.entityPackage.replace('.', '/') + "/";

		return path;
	}
	
	protected String getOutputDaoPath() {
		String path = this.outputPath + this.daoPackage.replace('.', '/') + "/";

		return path;
	}

	protected String getOutputSqlMapperPath() {
		String path = this.outputPath + this.sqlMapperPath + "/";

		return path;
	}

	protected String getOutputUiModelDataPath() {
		String path = this.outputPath + this.uiModelDataPath + "/";

		return path;
	}

	protected String getOutputServicePath() {
		String path = this.outputPath + this.servicePackage.replace('.', '/') + "/";

		return path;
	}

	protected String getOutputServiceImplPath() {
		String path = this.outputPath + this.serviceImplPackage.replace('.', '/') + "/";

		return path;
	}

	protected String getOutputActionPath() {
		String path = this.outputPath + this.actionPackage.replace('.', '/') + "/";

		return path;
	}

	protected String getOutputHtmlPath() {
		String path = this.outputPath + this.htmlPath + "/";

		return path;
	}

	protected String getOutputMenuDataPath() {
		String path = this.outputPath + this.menuDataPath + "/";

		return path;
	}

	protected String getResource(String resourceName) {
		URL url = this.getClass().getResource(resourceName);
		String fileName = url.getFile();

		return fileName;
	}

	protected String capFirst(String str) {
		String strRet = str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());

		return strRet;
	}

	/**
	 * 转换成驼峰式
	 * 
	 * @param str
	 *            ：原字符串
	 * @param isFirstUpper
	 *            ：是否首字母大写
	 * @return 转换后的驼峰式名字
	 */
	protected String name2HumpFormat(String str, boolean isFirstUpper) {
		String strRet = "";
		String[] splits = str.split("_");
		for (int i = 0; i < splits.length; i++) {
			String s = splits[i];
			if (!"".equals(s)) {
				String temp = s;
				if (isFirstUpper) {
					temp = capFirst(s);
				}
				if (i > 0) {
					temp = capFirst(s);
				}

				strRet += temp;
			}
		}

		return strRet;
	}

	protected EngineUtil createEngineUtil() {
		EngineUtil oEngineUtil = new EngineUtil();
		oEngineUtil.setOutputPath(this.getOutputPath());
		return oEngineUtil;
	}

	protected EngineUtilEx createEngineUtilEx() {
		EngineUtilEx oEngineUtil = new EngineUtilEx();
		oEngineUtil.setOutputPath(this.getOutputPath());
		return oEngineUtil;
	}
}