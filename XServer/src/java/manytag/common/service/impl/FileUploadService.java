package manytag.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import manytag.common.annotation.SystemLog;
import manytag.common.dao.entity.SystemResourceUploadEntity;
import manytag.common.service.IFileUploadService;
import manytag.common.service.ISystemResourceUploadService;
import manytag.common.session.LoginUser;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.util.UUIDBuilder;

@Service("fileUploadService")
public class FileUploadService implements IFileUploadService {
	public static final String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";
	public static final String FORM_DATA = "FORM_DATA";

	private String uploadfiles = "/uploadfiles";
	private String uploadtmp = "/uploadtmp";
	private ISystemResourceUploadService systemResourceUploadService = null;

	public String getUploadfiles() {
		return uploadfiles;
	}

	public void setUploadfiles(String uploadfiles) {
		this.uploadfiles = uploadfiles;
	}

	public String getUploadtmp() {
		return uploadtmp;
	}

	public void setUploadtmp(String uploadtmp) {
		this.uploadtmp = uploadtmp;
	}

	@SystemLog(module = "产品授权信息", methods = "文件上传")
	public Map<String, Object> uploadfile(HttpServletRequest request) {
		systemResourceUploadService = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		// 文件上传处理后的返回结果，包括上传文件的路径和非上传文件数据
		Map<String, Object> returnval = new HashMap<String, Object>();
		// 非上传文件的数据
		List<FileItem> formItems = new ArrayList<FileItem>();
		// 上传文件路径
		Map<String, String> upfilesurlsMap = new HashMap<String, String>();

		String fileName = null;
		String storePath = request.getSession().getServletContext().getRealPath(getUploadfiles());

		try {
			File storePathF = new File(storePath);
			if (!storePathF.exists()) {
				storePathF.mkdirs();
			}
			request.setCharacterEncoding("UTF-8");

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置临时文件的存放路径
			File tmp = new File(request.getSession().getServletContext().getRealPath(getUploadtmp()));
			if (!tmp.exists()) {
				tmp.mkdirs();
			}
			factory.setRepository(tmp);
			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setProgressListener(new ProgressListener() {
//				public void update(long pBytesRead, long pContentLength, int pItems) {
//					System.out.println("已读" + pBytesRead + ",总大小:" + pContentLength + ",第几项:" + pItems + "上传进度="
//							+ (pBytesRead / pContentLength * 100) + "%");
//					System.out.println(pBytesRead / pContentLength);
//				}
//			});
			// 设置单个文件的大小
			upload.setFileSizeMax(12 * 1024 * 1024);
			upload.setSizeMax(40 * 1024 * 1024);

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						formItems.add(item);
//						fileName = item.getFieldName();
//						String value = item.getString("UTF-8");
//						this.getHttpContext().getRequest().setAttribute(fileName, value);
					} else {

						String mimeType = item.getContentType();
						if (mimeType != null) {
							InputStream in = item.getInputStream(); // 文件内容的输入流
							fileName = item.getName(); // 上传文件的文件名
							String fieldName = item.getFieldName(); //fileid
							String id = fieldName.substring(0, fieldName.lastIndexOf("_"));
							if (fileName == null || "".equals(fileName)) {
								continue;
							}
							fileName = fileName.replace('\\', '/');
							fileName = fileName.substring(fileName.lastIndexOf("/") + 1); // 原来的文件名
//							System.out.println(request.getRemoteAddr() + "上传了" + fileName);
							String uid = UUIDBuilder.getUUID();
							String uidfileName = uid + fileName.substring(fileName.indexOf("."), fileName.length());
							File file = new File(storePath, uidfileName);

							OutputStream out = new FileOutputStream(file);

							int len = -1;
							byte[] b = new byte[1024];
							while ((len = in.read(b)) != -1) {
								out.write(b, 0, len);
							}
							in.close();
							out.close();
							item.delete(); // 删除临时文件
							insertUploadFile(fileName, uid, uidfileName, request);

							if (upfilesurlsMap.get(id) == null) {
								upfilesurlsMap.put(id, uid);
							} else {
								String upfilesurls = upfilesurlsMap.get(id);
								upfilesurls += "," + uid;
								upfilesurlsMap.put(id, upfilesurls);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		returnval.put(UPLOAD_FILE_PATH, upfilesurlsMap);
		returnval.put(FORM_DATA, formItems);

		return returnval;
	}

	private int insertUploadFile(String fileName, String uid, String uidfileName, HttpServletRequest request) throws Exception {
		SystemResourceUploadEntity systemResourceUploadEntity = new SystemResourceUploadEntity();
		systemResourceUploadEntity.setUid(uid);
		systemResourceUploadEntity.setResouceUrl(getUploadfiles() + "/" + uidfileName);
		systemResourceUploadEntity.setMemo(fileName);
		systemResourceUploadEntity.setCreateTime(new Date());
		systemResourceUploadEntity.setCreateUser(getLoginUser(request));
		return systemResourceUploadService.insert(systemResourceUploadEntity);
	}

	public String getLoginUser(HttpServletRequest request) {
		LoginUser user = (LoginUser) request.getSession(true).getAttribute(Constants.SESSION_USER);
		return user.getUserInfo().getCode();
	}
}