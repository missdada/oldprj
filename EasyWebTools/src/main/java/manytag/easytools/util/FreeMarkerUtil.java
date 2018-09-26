package manytag.easytools.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {
	public static Template getTemplate(String templateFileName) throws IOException {
		String basePath = getDirectory(templateFileName);
		String fileName = getFileName(templateFileName);

		Configuration freeMarkerCfg = new Configuration(Configuration.VERSION_2_3_0);
		freeMarkerCfg.setDefaultEncoding("UTF-8");
//		freeMarkerCfg.setClassForTemplateLoading(getClass(), basePath);
		freeMarkerCfg.setDirectoryForTemplateLoading(new File(basePath));
		freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));

		Template template = freeMarkerCfg.getTemplate(fileName);
		template.setEncoding("UTF-8");

		return template;
	}

	public static String merge(String templateFileName, Map<String, Object> parameters) {
		String resultString = null;
		try {
			Template template = getTemplate(templateFileName);
			StringWriter stringWriter = new StringWriter();
			template.process(parameters, stringWriter);
			resultString = stringWriter.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return resultString;
	}

	public static String getDirectory(String sFileName) {
		String sDir = sFileName;
		if (sFileName != null) {
			int nIndex = sFileName.lastIndexOf('/');
			if (nIndex == -1) {
				nIndex = sFileName.lastIndexOf('\\');
			}
			if (nIndex != -1) {
				sDir = sFileName.substring(0, nIndex + 1);
			}
		}

		return sDir;
	}

	public static String getFileName(String sFileName) {
		String sDir = getDirectory(sFileName);
		String sName = sFileName.substring(sDir.length());

		return sName;
	}
}