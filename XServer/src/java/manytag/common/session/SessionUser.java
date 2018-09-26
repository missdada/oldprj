package manytag.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manytag.framework.dispatch.base.Constants;

public class SessionUser {
	/**
	 * 获取session中的用户信息
	 * 
	 * @param request
	 * @return ManageWechatFollowedUsersEntity。若session中不存在用户信息，则返回null。
	 */
	public static LoginUser getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		LoginUser sessionUserEntity = (LoginUser) session.getAttribute(Constants.SESSION_USER);
		return sessionUserEntity;
	}

	/**
	 * 在session中设置用户信息
	 * 
	 * @param request
	 * @param userEntity
	 */
	public static void setUser(HttpServletRequest request, LoginUser userEntity) {
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_USER, userEntity);
	}
}