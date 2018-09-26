package manytag.common.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemMonitorLoginEntity;
import manytag.common.dao.entity.SystemRolesEntity;
import manytag.common.dao.entity.SystemRolesSearchEntity;
import manytag.common.dao.entity.SystemUserRoleEntity;
import manytag.common.dao.entity.SystemUserRoleSearchEntity;
import manytag.common.dao.entity.SystemUsersEntity;
import manytag.common.dao.entity.SystemUsersSearchEntity;
import manytag.common.pub.CommonUtil;
import manytag.common.service.ISearchMenuService;
import manytag.common.service.ISystemMonitorLoginService;
import manytag.common.service.ISystemRolesService;
import manytag.common.service.ISystemUserRoleService;
import manytag.common.service.ISystemUsersService;
import manytag.common.session.LoginUser;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("login")
@Scope("prototype")
public class LoginAction extends BaseAction {
	public void login() throws Exception {
		JsonResponse result = new JsonResponse();

		SystemUsersSearchEntity entity = this.paserJsonToEntity(SystemUsersSearchEntity.class);
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		SystemUsersEntity user = service.searchPK(entity);

		ISystemMonitorLoginService loginMonitorService = ApplicationContext.getBean("systemMonitorLoginService", ISystemMonitorLoginService.class);
		if (user == null) {
			// 用户名未找到，登录失败
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E9001", new String[] { entity.getCode() }));
			this.setResult(result);
			//记录登录信息
			SystemMonitorLoginEntity loginInfo = new SystemMonitorLoginEntity();
			super.setUidDateTime(loginInfo);
			loginInfo.setIp(CommonUtil.getIpAddr(super.getHttpContext().getRequest()));
			loginInfo.setUserCode(entity.getCode());
			loginInfo.setLoginTime(new Date());
			loginInfo.setMemo(result.getMessage().toString());

			loginMonitorService.insert(loginInfo);
			return;
		} else {
			String passwordDB = user.getPassword();
			if (!passwordDB.equals(entity.getPassword())) {
				// 密码不匹配，登录失败
				result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E9001", new String[] { entity.getCode() }));
				this.setResult(result);
				//记录登录信息
				SystemMonitorLoginEntity loginInfo = new SystemMonitorLoginEntity();
				super.setUidDateTime(loginInfo);
				loginInfo.setIp(CommonUtil.getIpAddr(super.getHttpContext().getRequest()));
				loginInfo.setUserCode(entity.getCode());
				loginInfo.setLoginTime(new Date());
				loginInfo.setMemo(result.getMessage().toString());

				loginMonitorService.insert(loginInfo);
				return;
			}

			// 登录成功，取得用户的角色和权限信息
			LoginUser loginUser = new LoginUser();
			loginUser.setUserInfo(user);
			this.getHttpContext().getSession(true).setAttribute(Constants.SESSION_USER, loginUser);

			// 根据用户id取得用户角色
			ISystemUserRoleService userRoleService = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
			SystemUserRoleSearchEntity systemUserRoleSearchEntity = new SystemUserRoleSearchEntity();
			systemUserRoleSearchEntity.setUserCode(user.getCode());
			List<SystemUserRoleEntity> userRoleList = userRoleService.selectAccurate(systemUserRoleSearchEntity);
			if (userRoleList != null && userRoleList.size() > 0) {
				ISearchMenuService searchMenuService = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
				for (SystemUserRoleEntity userRole : userRoleList) {
					loginUser.addRole(userRole.getRoleCode());
					// 根据用户角色取得对应的菜单项的action
					List<HashMap<String, Object>> menuList = searchMenuService.roleMenuActionSearch(userRole.getRoleCode());
					if (menuList != null && menuList.size() > 0) {
						for (HashMap<String, Object> menu : menuList) {
							// 将角色对应的菜单操作放入权限列表
							String url = (String) menu.get("url");
							loginUser.addStringPermission(url);
							if (url != null && !"".equals(url)) {
								if ("*".equals(url) || url.contains(":*")) {
									//管理员，所有权限
									loginUser.addMenus("BTN_ALLPERMIS");
								}
							}
							String m = (String) menu.get("menu");
							if (m != null && !"".equals(m)) {
								loginUser.addMenus(m);
							}
						}
					}
				}
			}
			result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0000", new String[] {}));
			this.setResult(result);
			//记录登录信息
			SystemMonitorLoginEntity loginInfo = new SystemMonitorLoginEntity();
			super.setUidDateTime(loginInfo, false, true, true);
			loginInfo.setIp(CommonUtil.getIpAddr(super.getHttpContext().getRequest()));
			loginInfo.setUserCode(entity.getCode());
			loginInfo.setLoginTime(new Date());
			loginInfo.setMemo(result.getMessage().toString());

			loginMonitorService.insert(loginInfo);
		}
	}

	public void logout() throws Exception {
		LoginUser loginUser = (LoginUser) this.getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
		if (loginUser != null) {
			this.getHttpContext().getSession(true).removeAttribute(Constants.SESSION_USER);
		}
		JsonResponse result = new JsonResponse();
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I9999", new String[] {}));
		this.setResult(result);
		//记录登录信息
		ISystemMonitorLoginService loginMonitorService = ApplicationContext.getBean("systemMonitorLoginService", ISystemMonitorLoginService.class);
		SystemMonitorLoginEntity loginInfo = new SystemMonitorLoginEntity();
		super.setUidDateTime(loginInfo, false, true, true);
		loginInfo.setIp(CommonUtil.getIpAddr(super.getHttpContext().getRequest()));
		loginInfo.setUserCode(loginUser.getUserInfo().getCode());
		loginInfo.setLoginTime(new Date());
		loginInfo.setMemo(loginInfo.getUserCode() + " 退出登录.");

		loginMonitorService.insert(loginInfo);
	}

	public void getLoginUserInfo() {
		LoginUser loginUser = (LoginUser) this.getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
		if (loginUser == null) {
			// 未登录的场合，重新登录
			JsonResponse jr = new JsonResponse();
			jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0000", new String[] {}));
			this.setResult(jr);
			return;
		}
		JsonResponse result = new JsonResponse();
		result.put("userinfo", loginUser.getUserInfo());
		this.setResult(result);
	}

	public void getLoginUserRole() throws Exception {
		LoginUser loginUser = (LoginUser) this.getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
		if (loginUser == null) {
			// 未登录的场合，重新登录
			JsonResponse jr = new JsonResponse();
			jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0000", new String[] {}));
			this.setResult(jr);
			return;
		}
		// 根据用户id取得用户角色
		Set<String> userRoles = loginUser.getRoles();
		List<SystemRolesEntity> userRoleList = new ArrayList<SystemRolesEntity>();
		SystemRolesSearchEntity entity = new SystemRolesSearchEntity();
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		for (String role : userRoles) {
			entity.setUid(role);
			List<SystemRolesEntity> searchList = service.search(entity);
			if (searchList != null && searchList.size() > 0) {
				userRoleList.addAll(searchList);
			}
		}
		JsonResponse result = new JsonResponse();
		result.put("total", userRoleList.size());
		result.put("rows", userRoleList);
		this.setResult(result);
	}
}