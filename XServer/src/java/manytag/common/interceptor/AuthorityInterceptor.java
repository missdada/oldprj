package manytag.common.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import manytag.business.base.ICommonProcess;
import manytag.common.annotation.SystemLog;
import manytag.common.dao.entity.SystemMonitorOperationEntity;
import manytag.common.pub.CommonUtil;
import manytag.common.service.ISystemMonitorOperationService;
import manytag.common.session.LoginUser;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.ServerResponse;
import manytag.framework.dispatch.base.response.impl.JsonResponse;
import manytag.framework.dispatch.service.core.interceptor.BaseInterceptor;

public class AuthorityInterceptor extends BaseInterceptor {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private List<String> exclude = new ArrayList<String>();

	@Override
	public ServerResponse intercept(BaseAction invocation, String method) throws Exception {
		String actionPath = invocation.getClass().getName() + ":" + method;
		if (log.isDebugEnabled()) {
			printOutRequestParameters(actionPath, invocation.getHttpContext().getRequest());
		} else {
			log.info("[访问路径] [" + actionPath + "]");
		}
		LoginUser user = null;
		ServerResponse res = null;
		if (!checkExclude(actionPath)) {
			user = (LoginUser) invocation.getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
			if (user == null) {
				log.error("[访问路径] [" + actionPath + "]被拒绝! 没有登录");
				// 未登录的场合，重新登录
				JsonResponse jr = new JsonResponse();
				jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0000", new String[] {}));
				return jr;
			} else {
				// 权限检查
				if (!user.isPermitted(actionPath)) {
					log.error("[访问路径] [" + actionPath + "]被拒绝! 该用户[" + user.getUserInfo().getCode() + "]没有这个权限");
					// 如果用户没有访问这个action的权限
					JsonResponse jr = new JsonResponse();
					jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0002", new String[] {}));
					return jr;

				}
			}
			String ip = CommonUtil.getIpAddr(invocation.getHttpContext().getRequest());
			Long start = System.currentTimeMillis();
//			if (invocation instanceof BaseBusicessAction) {
//				invocation.execute("myPrepare");
//			}
			if (invocation instanceof ICommonProcess) {
				((ICommonProcess) invocation).doActionBefor();
			}
			res = invocation.execute(method);
			if (invocation instanceof ICommonProcess) {
				((ICommonProcess) invocation).doActionAfter();
			}
			if (res instanceof JsonResponse) { // 如果是JSON的记录日志
				getControllerMethodDescription(user, invocation.getClass(), method, res, start, ip);
			}
		} else {
			if (invocation instanceof ICommonProcess) {
				((ICommonProcess) invocation).doActionBefor();
			}
			res = invocation.execute(method);
			if (invocation instanceof ICommonProcess) {
				((ICommonProcess) invocation).doActionAfter();
			}
		}

		return res;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public void getControllerMethodDescription(LoginUser user, Class classes, String methodName, ServerResponse res, Long start, String ip)
			throws Exception {
		Method[] methods = classes.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				if (method.getAnnotation(SystemLog.class) != null) {
					String de = method.getAnnotation(SystemLog.class).description();
					if (de == null || de.equals("")) {
						de = " ";
					}
					Long time = System.currentTimeMillis() - start;
					ISystemMonitorOperationService service = ApplicationContext.getBean("systemMonitorOperationService",
							ISystemMonitorOperationService.class);
					SystemMonitorOperationEntity entity = new SystemMonitorOperationEntity();
					entity.setUserCode(user.getUserInfo().getCode());
					entity.setResponseTime(time.intValue());
					entity.setModelContent(method.getAnnotation(SystemLog.class).module());
					entity.setFunctionContent(method.getAnnotation(SystemLog.class).methods());
					entity.setIp(ip);
					if (res.getMessage() != null) {
						entity.setOperationResult(res.getMessage().getMessage());
					} else {
						entity.setOperationResult("执行成功！");
					}
					entity.setMemo(de);
					entity.setCreateTime(new java.util.Date());
					service.insert(entity);
					break;
				}
			}
		}
	}

	/*
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	*/

	private void printOutRequestParameters(String actionPath, HttpServletRequest request) {
		log.debug("[请求路径下的参数] [" + actionPath + "]");
		Map<String, String[]> parameters = request.getParameterMap();
		for (String key : parameters.keySet()) {
			String values[] = parameters.get(key);
			StringBuilder sb = new StringBuilder();
			for (String value : values) {
				sb.append(value);
				sb.append(";");
			}
			log.debug(key + "=" + sb.toString());
		}
	}

	private boolean checkExclude(String actionURL) {
		if (exclude == null || exclude.size() == 0) {
			return false;
		}
		if (actionURL == null || "".equals(actionURL)) {
			return false;
		}

		for (String skipurl : exclude) {
			if (actionURL.equals(skipurl.trim())) {
				return true;
			}
		}
		return false;
	}

	public List<String> getExclude() {
		return exclude;
	}

	public void setExclude(List<String> exclude) {
		this.exclude = exclude;
	}
}