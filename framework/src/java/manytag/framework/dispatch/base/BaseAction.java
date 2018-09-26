package manytag.framework.dispatch.base;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.impl.JsonResponse;
import manytag.framework.dispatch.service.HttpContext;
import manytag.framework.util.StringUtils;
import manytag.framework.util.UUIDBuilder;

public class BaseAction {
	protected static final Logger log = LoggerFactory.getLogger(BaseAction.class);

	protected HttpContext httpContext;
	private ServerResponse result = null;
	private ObjectMapper mapper = new ObjectMapper();

	public HttpContext getHttpContext() {
		return httpContext;
	}

	public HttpServletRequest getRequest() {
		return httpContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return httpContext.getResponse();
	}

	public HttpSession getSession() {
		return httpContext.getSession();
	}

	public void setHttpContext(HttpContext httpContext) {
		this.httpContext = httpContext;
	}

	public ServerResponse getResult() {
		return result;
	}

	public void setResult(ServerResponse result) {
		this.result = result;
	}

	public String getParameter(String key) {
		return httpContext.getRequest().getParameter(key);
	}

	public void addAttribute(String key, Object value) {
		httpContext.getRequest().setAttribute(key, value);
	}

	public Object getAttribute(String key) {
		return httpContext.getRequest().getAttribute(key);
	}

	public ServerResponse execute(String method) {
		try {
			Method myMethod = this.getClass().getMethod(method, (Class[]) null);
			myMethod.invoke(this, (Object[]) null);
		} catch (Exception e) {
			log.error("", e);
			if (result != null) {
				result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0001", new String[] {}));
			} else {
				JsonResponse jr = new JsonResponse();
				jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0001", e.getMessage()));
				this.setResult(jr);
			}
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T paramToEntity(Class<?> beanClass) throws Exception {
		Map paramList = this.httpContext.getRequest().getParameterMap();
		return (T) mapToObject(paramList, beanClass);
	}

	public <T> T mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		@SuppressWarnings("unchecked")
		Class<T> c = (Class<T>) Class.forName(beanClass.getName());

		T obj = (T) c.newInstance();

		BeanUtils.populate(obj, map);

		return obj;
	}

	public <T> T paserJsonToEntity(Class<T> o) throws PaserJsonException {
		String param_json = httpContext.getRequest().getParameter(Constants.STR_JSON);
		if (param_json == null || "".equals(param_json)) {
			param_json = (String) httpContext.getRequest().getAttribute(Constants.STR_JSON);
		}
		param_json = unicodeToString(param_json);
		return paserJsonToEntity(param_json, o);
	}

	public <T> T paserJsonToEntity(String param_json, Class<T> o) throws PaserJsonException {
		try {
			@SuppressWarnings("unchecked")
			Class<T> c = (Class<T>) Class.forName(o.getName());

			T instance = (T) c.newInstance();
			if (param_json != null && !"".equals(param_json)) {
				instance = mapper.readValue(param_json, o);
			}

			if (instance instanceof BaseEntity) {
				getPaginationInfo((BaseEntity) instance);
			}
			getUploadFileInfo(instance);
			return instance;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			throw new PaserJsonException(e);
		}
	}

	private void getPaginationInfo(BaseEntity instance) {
		String limit = (String) httpContext.getRequest().getParameter("limit");
		String off = (String) httpContext.getRequest().getParameter("offset");
		if (limit != null && !"".equals(limit) && off != null && !"".equals(off)) {
			int l = Integer.parseInt(limit);

			int offset = Integer.parseInt(off);
			int p = offset / l;
			((BaseEntity) instance).setPage(p);
			((BaseEntity) instance).setRows(l);
			((BaseEntity) instance).setOffset(offset);
		}
	}

	@SuppressWarnings("unchecked")
	private void getUploadFileInfo(Object instance) {
		Map<String, String> uploadfileUidsMap = (Map<String, String>) httpContext.getRequest().getAttribute(Constants.STR_UPLOADFILES);

		String uids = (String) httpContext.getRequest().getAttribute(Constants.STR_UPLOADFILES_UIDS);

		Map<String, String> uidsMap = chageUids2MapByCloum(uids);

		Map<String, String> mergUrls = mergUrls(uploadfileUidsMap, uidsMap);
		if (mergUrls != null && !mergUrls.isEmpty()) {
			for (Map.Entry<String, String> entry : mergUrls.entrySet()) {
				String urlcolum = entry.getKey();
				String urls = entry.getValue();
				// merg uids
				Field field = null;
				Method method = null;

				try {
					field = instance.getClass().getDeclaredField(urlcolum);
					if (field != null) {
						method = instance.getClass().getMethod("set" + StringUtils.uppercaseFirstChar(urlcolum), new Class[] { String.class });
						if (method != null) {
							method.invoke(instance, urls);
						}
					}
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		} else {

		}

//		if (uploadfile_uids != null && !"".equals(uploadfile_uids)) {
//			if (all != null && !"".equals(all)) {
//				all = uploadfile_uids + "," + all;
//			} else {
//				all = uploadfile_uids;
//			}
//		}
//		if (uids != null && !"".equals(uids)) {
//			if (all != null && !"".equals(all)) {
//				all = uids + "," + all;
//			} else {
//				all = uids;
//			}
//		}
//		((BaseEntity) instance).setUploadfile_uids(all);
	}

	private Map<String, String> mergUrls(Map<String, String> urlsMap1, Map<String, String> urlsMap2) {
		if (urlsMap1 == null && urlsMap2 == null) {
			return null;
		}
		if (urlsMap1 == null && urlsMap2 != null) {
			return urlsMap2;
		}
		if (urlsMap1 != null && urlsMap2 == null) {
			return urlsMap1;
		}

		Map<String, String> mergMap = new HashMap<String, String>();
		if (urlsMap1 != null && !urlsMap1.isEmpty()) {
			for (Map.Entry<String, String> entry : urlsMap1.entrySet()) {
				String urlcolum = entry.getKey();
				String urls = entry.getValue();

				if (urlsMap2.containsKey(urlcolum)) {
					String urls2 = urlsMap2.get(urlcolum);
					mergMap.put(urlcolum, urls + "," + urls2);
					urlsMap2.remove(urlcolum);
				} else {
					mergMap.put(urlcolum, urls);
				}
			}
		}
		if (urlsMap2 != null && !urlsMap2.isEmpty()) {
			for (Map.Entry<String, String> entry : urlsMap2.entrySet()) {
				String urlcolum = entry.getKey();
				String urls = entry.getValue();
				mergMap.put(urlcolum, urls);
			}
		}
		return mergMap;
	}

	/**
	 * aaa_1,aaa_2,aaa_3,bbb_1,bbb_2 -> aaa 1,2,3 bbb 1,2
	 * 
	 * @param uids
	 * @return
	 */
	private Map<String, String> chageUids2MapByCloum(String uids) {
		if (StringUtils.checkBlank(uids)) {
			return null;
		}
		String[] uidlist = null;
		uidlist = uids.split(",");

		Map<String, String> uidsMap = new HashMap<String, String>();
		for (String u : uidlist) {
			String id = u.substring(0, u.indexOf("_"));
			String url = u.substring(u.indexOf("_") + 1);
			String urls = "";
			if (uidsMap.containsKey(id)) {
				urls = uidsMap.get(id);
				urls += "," + url;
			} else {
				urls += url;
			}
			uidsMap.put(id, urls);
		}
		return uidsMap;
	}

	public <T> List<T> paserJsonToArray(Class<T[]> o) {
		String param_json = httpContext.getRequest().getParameter(Constants.STR_JSON);
		param_json = unicodeToString(param_json);
		return paserJsonToArray(param_json, o);
	}

	public <T> List<T> paserJsonToArray(String param_json, Class<T[]> o) {
		List<T> paramList = new ArrayList<T>();
		if (param_json != null && !"".equals(param_json)) {
			try {
				T[] ins = (T[]) mapper.readValue(param_json, o);
				paramList = Arrays.asList(ins);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (T b : paramList) {
			if (b instanceof BaseEntity) {
				getPaginationInfo((BaseEntity) b);
				getUploadFileInfo((BaseEntity) b);
			}
		}

		return paramList;
	}

	public String parseEntity2Json(BaseEntity entity) {
		StringWriter str = new StringWriter();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(str, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public <T> String parseEntity2Json(List<T> list) {
		StringWriter str = new StringWriter();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(str, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
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

	public static String unicodeToString(String str) {
		if (str != null) {
			str = str.replace("%u", "\\u");
			Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
			Matcher matcher = pattern.matcher(str);
			char ch;
			while (matcher.find()) {
				ch = (char) Integer.parseInt(matcher.group(2), 16);
				str = str.replace(matcher.group(1), ch + "");
			}
			str = str.replace("%20", " ");
		}
		return str;
	}
}