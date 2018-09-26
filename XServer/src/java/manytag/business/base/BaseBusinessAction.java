package manytag.business.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import manytag.common.dao.entity.DicSearchEntity;
import manytag.common.dao.entity.DropDownResultEntity;
import manytag.common.dao.entity.SystemResourceUploadEntity;
import manytag.common.dao.entity.SystemResourceUploadSearchEntity;
import manytag.common.dao.entity.TableSearchEntity;
import manytag.common.service.IFileUploadService;
import manytag.common.service.IGetDropDownService;
import manytag.common.service.ISystemResourceUploadService;
import manytag.common.service.impl.FileUploadService;
import manytag.common.session.LoginUser;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.util.JsonUtil;
import manytag.framework.util.UUIDBuilder;

public class BaseBusinessAction extends BaseAction implements ICommonProcess {
	public void doActionBefor() throws Exception {
		// 文件上传后处理
		HttpServletRequest request = this.getHttpContext().getRequest();

		if (ServletFileUpload.isMultipartContent(request)) {
			IFileUploadService fileUploadService = ApplicationContext.getBean("fileUploadService", IFileUploadService.class);
			Map<String, Object> uploadresult = fileUploadService.uploadfile(request);

			// 处理表单数据 主要是_json 将数据放到session里
			List<FileItem> formItems = (List<FileItem>) uploadresult.get(FileUploadService.FORM_DATA);
			if (formItems != null && formItems.size() > 0) {
				for (FileItem item : formItems) {
					String fileName = item.getFieldName();
					String value = item.getString("UTF-8");
					request.setAttribute(fileName, value);
				}
			}

			// 处理上传文件路径
			Map<String, String> upfilesurlsMap = (Map<String, String>) uploadresult.get(FileUploadService.UPLOAD_FILE_PATH);
			if (upfilesurlsMap != null && !upfilesurlsMap.isEmpty()) {
				request.setAttribute(Constants.STR_UPLOADFILES, upfilesurlsMap);
			}
		}
	}

	public void doActionAfter() throws Exception {

	}

	/**
	 * @param uids: 资源uid
	 * @return json字符串。格式：[{"uid":资源UID, "url":资源url,
	 *         "thumbnail_url":图片资源的缩略图url, "file_name":资源的原始名称},{...}]
	 * @throws Exception
	 */
	protected String getImageUrls(String uids) throws Exception {
		String urls = null;
		if (uids != null && !"".equals(uids)) {
			List<Map<String, String>> resourceList = new ArrayList<Map<String, String>>();
			urls = "";
			ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
			String[] uidSplit = uids.split(",");
			for (String uid : uidSplit) {
				Map<String, String> items = new HashMap<String, String>();
				resourceList.add(items);

				SystemResourceUploadSearchEntity entity = new SystemResourceUploadSearchEntity();
				entity.setUid(uid);
				List<SystemResourceUploadEntity> searchList = service.search(entity);
				if (searchList.size() > 0) {
					SystemResourceUploadEntity oSystemResourceUploadEntity = searchList.get(0);
					items.put("uid", uid);
					items.put("url", oSystemResourceUploadEntity.getResouceUrl());
					items.put("thumbnail_url", oSystemResourceUploadEntity.getThumbnailUrl());
					items.put("file_name", oSystemResourceUploadEntity.getMemo());
				}
			}
			urls = JsonUtil.obj2Str(resourceList);
		}

		return urls;
	}

	public String getNameFromTable(String tableName, String valueColumn, String nameColumn, String ids) throws Exception {
		String names = null;
		if (ids != null && !"".equals(ids)) {
			names = "";
			IGetDropDownService service = ApplicationContext.getBean("getDropDownService", IGetDropDownService.class);
			String[] uidSplit = ids.split(",");
			for (String uid : uidSplit) {
				TableSearchEntity entity = new TableSearchEntity();
				entity.setTableName(tableName);
				entity.setValueColumn(valueColumn);
				entity.setNameColumn(nameColumn);
				entity.setWhere(valueColumn + "='" + uid + "'");
				List<DropDownResultEntity> searchList = service.tableSearch(entity);
				if (searchList.size() > 0) {
					DropDownResultEntity oDropDownResultEntity = searchList.get(0);
					names += oDropDownResultEntity.name;
					names += ",";
				}
			}
			if (names.length() > 0) {
				names = names.substring(0, names.length() - 1);
			}
		}

		return names;
	}

	public String getNameFromDic(String code, String ids) throws Exception {
		String names = null;
		if (ids != null && !"".equals(ids)) {
			names = "";
			IGetDropDownService service = ApplicationContext.getBean("getDropDownService", IGetDropDownService.class);
			String[] uidSplit = ids.split(",");
			for (String uid : uidSplit) {
				DicSearchEntity entity = new DicSearchEntity();
				entity.setCode(code);
				entity.setItemKey(uid);

				List<DropDownResultEntity> searchList = service.dicSearch(entity);
				if (searchList.size() > 0) {
					DropDownResultEntity oDropDownResultEntity = searchList.get(0);
					names += oDropDownResultEntity.name;
					names += ",";
				}
			}
			if (names.length() > 0) {
				names = names.substring(0, names.length() - 1);
			}
		}

		return names;
	}

	public LoginUser getLoginUser() {
		LoginUser user = (LoginUser) getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
		return user;
	}

	public void setUidLongTime(Object entity, boolean setUid, boolean setCreateTime, boolean setUpdateTime) {
		if (entity == null) {
			return;
		}
		try {
			Field field = null;
			Method method = null;
			if (setUid) {
				field = entity.getClass().getDeclaredField("uid");
				if (field != null) {
					method = entity.getClass().getMethod("setUid", new Class[] { String.class });
					method.invoke(entity, UUIDBuilder.getUUID());
				}
			}
			Long now = null;

			if (setCreateTime) {
				field = entity.getClass().getDeclaredField("createTime");
				if (field != null) {
					now = System.currentTimeMillis();
					method = entity.getClass().getMethod("setCreateTime", new Class[] { Long.class });
					method.invoke(entity, now);
				}
				field = entity.getClass().getDeclaredField("createUser");
				if (field != null) {
					method = entity.getClass().getMethod("setCreateUser", new Class[] { String.class });
					method.invoke(entity, getLoginUser().getUserInfo().getCode());
				}
			}
			if (setUpdateTime) {
				field = entity.getClass().getDeclaredField("updateTime");
				if (field != null) {
					if (now == null) {
						now = System.currentTimeMillis();
					}
					method = entity.getClass().getMethod("setUpdateTime", new Class[] { Long.class });
					method.invoke(entity, now);
				}
				field = entity.getClass().getDeclaredField("updateUser");
				if (field != null) {
					method = entity.getClass().getMethod("setUpdateUser", new Class[] { String.class });
					method.invoke(entity, getLoginUser().getUserInfo().getCode());
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void setUidLongTime(Object entity) {
		setUidLongTime(entity, true, true, true);
	}

	public void setUidDateTime(Object entity, boolean setUid, boolean setCreateTime, boolean setUpdateTime) {
		if (entity == null) {
			return;
		}
		try {
			Field field = null;
			Method method = null;
			if (setUid) {
				try {
					field = entity.getClass().getDeclaredField("uid");
					if (field != null) {
						method = entity.getClass().getMethod("setUid", new Class[] { String.class });
						method.invoke(entity, UUIDBuilder.getUUID());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Date now = null;
			if (setCreateTime) {
				try {
					field = entity.getClass().getDeclaredField("createTime");
					if (field != null) {
						now = new Date();
						method = entity.getClass().getMethod("setCreateTime", new Class[] { Date.class });
						method.invoke(entity, now);
					}
					field = entity.getClass().getDeclaredField("createUser");
					if (field != null) {
						method = entity.getClass().getMethod("setCreateUser", new Class[] { String.class });
						method.invoke(entity, getLoginUser().getUserInfo().getCode());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (setUpdateTime) {
				try {
					field = entity.getClass().getDeclaredField("updateTime");
					if (field != null) {
						if (now == null) {
							now = new Date();
						}
						method = entity.getClass().getMethod("setUpdateTime", new Class[] { Date.class });
						method.invoke(entity, now);
					}
					field = entity.getClass().getDeclaredField("updateUser");
					if (field != null) {
						method = entity.getClass().getMethod("setUpdateUser", new Class[] { String.class });
						method.invoke(entity, getLoginUser().getUserInfo().getCode());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUidDateTime(Object entity) {
		setUidDateTime(entity, true, true, true);
	}
}