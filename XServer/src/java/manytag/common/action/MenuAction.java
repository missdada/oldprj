package manytag.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemRoleMenusEntity;
import manytag.common.dao.entity.SystemRoleMenusSearchEntity;
import manytag.common.dao.entity.SystemRolesEntity;
import manytag.common.dao.entity.SystemUserRoleEntity;
import manytag.common.service.ISearchMenuService;
import manytag.common.service.ISystemRoleMenusService;
import manytag.common.service.ISystemUserRoleService;
import manytag.common.session.LoginUser;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Constants;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.FreemarkerResponse;
import manytag.framework.dispatch.base.response.impl.JsonResponse;
import manytag.framework.util.StringUtils;

@Controller("menu")
@Scope("prototype")
public class MenuAction extends BaseAction {
	public void doSearchMenu() throws Exception {
		ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
		List<HashMap<String, Object>> searchList = service.searchMenu();
		//JSONArray resourcesArray = JSONArray.fromObject(searchList);
		JsonResponse result = new JsonResponse();
		result.put("menuArray", searchList);
		this.setResult(result);
	}

	public void doSearchMenuTree() throws Exception {
		ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
		List<HashMap<String, Object>> searchList = service.searchMenuTree();
		//JSONArray resourcesArray = JSONArray.fromObject(searchList);
		JsonResponse result = new JsonResponse();
		result.put("menuArray", searchList);
		this.setResult(result);
	}

	public void roleMenuSearch() throws Exception {
		SystemRoleMenusEntity entity = this.paserJsonToEntity(SystemRoleMenusEntity.class);
		String role_uid = entity.getRoleUid();
		if (role_uid == null || "".equals(role_uid)) {
			return;
		}
		ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
		List<HashMap<String, Object>> searchList = service.roleMenuSearch(role_uid);
		//JSONArray menuArray = JSONArray.fromObject(searchList);
		JsonResponse result = new JsonResponse();
		result.put("menuArray", searchList);
		this.setResult(result);
	}

	public void getRoleMenu() throws Exception {
		List<HashMap<String, Object>> menuList = new ArrayList<HashMap<String, Object>>();
		SystemRolesEntity crrentRoleE = this.paserJsonToEntity(SystemRolesEntity.class);
		String crrentRole = crrentRoleE.getUid();

		if (!StringUtils.checkBlank(crrentRole)) {
			ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);

			List<HashMap<String, Object>> searchList = service.roleMenuSearch(crrentRole);
			if (searchList != null && searchList.size() > 0) {
				for (HashMap<String, Object> function_id : searchList) {
					String url = (String) function_id.get("url");

					if ("*".equals(url)) {
						// 所有菜单权限
						List<HashMap<String, Object>> userMenuList = service.searchMenu();

						//JSONArray menuArray = JSONArray.fromObject(userMenuList);
						JsonResponse result = new JsonResponse();
						result.put("menuArray", userMenuList);
						this.setResult(result);
						return;
					}
				}
				menuList.addAll(searchList);
			}
		}

		//JSONArray menuArray = JSONArray.fromObject(menuList);
		JsonResponse result = new JsonResponse();
		result.put("menuArray", menuList);
		this.setResult(result);
		return;
	}

	public void userMenuSearch() throws Exception {
		LoginUser user = (LoginUser) this.getHttpContext().getSession(true).getAttribute(Constants.SESSION_USER);
		if (user == null) {
			// 未登录的场合，重新登录
			JsonResponse jr = new JsonResponse();
			jr.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0000", new String[] {}));
			this.setResult(jr);
			return;
		}
		List<HashMap<String, Object>> menuList = new ArrayList<HashMap<String, Object>>();
		Set<String> roleList = user.getRoles();
		HashMap<String, HashMap<String, Object>> map = new HashMap<String, HashMap<String, Object>>();
		if (roleList != null && roleList.size() > 0) {
			ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
			menuList = service.getParentMenu("root");
			for (String role_uid : roleList) {
				List<HashMap<String, Object>> searchList = service.roleMenuSearch(role_uid);
				if (searchList != null && searchList.size() > 0) {
					for (HashMap<String, Object> function_id : searchList) {
						map.put((String) function_id.get("function_id"), function_id);
					}
				}
			}
			for (Entry<String, HashMap<String, Object>> entry : map.entrySet()) {
				String url = (String) entry.getValue().get("url");
				if ("*".equals(url)) {
					// 所有菜单权限
					List<HashMap<String, Object>> userMenuList = service.searchMenu();
					for (HashMap<String, Object> menu : userMenuList) {
						service.getAllParentMenu(menuList, menu);
					}
					this.addAttribute("menulist", menuList);
					this.setResult(new FreemarkerResponse("menu/systemmenu"));
					return;
				} else if (!"".equals(url) && !"@".equals(url)) {
					service.getAllParentMenu(menuList, entry.getValue());
				}
			}
		}
		this.addAttribute("menulist", menuList);
		this.setResult(new FreemarkerResponse("menu/systemmenu"));
	}

	public void updateRoleMenu() throws Exception {
		HashMap<String, Object> params = this.paserJsonToEntity(HashMap.class);
		String uid = (String) params.get("uid");
		List<String> functionids = (ArrayList<String>) params.get("functionids");
		if (!StringUtils.checkBlank(uid)) {
			ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
			SystemRoleMenusSearchEntity systemRoleMenusSearchEntity = new SystemRoleMenusSearchEntity();
			systemRoleMenusSearchEntity.setRoleUid(uid);
			List<SystemRoleMenusEntity> roleMenuList = service.search(systemRoleMenusSearchEntity);
			if (roleMenuList != null && roleMenuList.size() > 0) {
				// 先删除旧数据
				service.delete(roleMenuList);
			}
			if (functionids != null && functionids.size() > 0) {
				// 插入新数据
				for (String f : functionids) {
					SystemRoleMenusEntity systemRoleMenusEntity = new SystemRoleMenusEntity();
					systemRoleMenusEntity.setRoleUid(uid);
					systemRoleMenusEntity.setMenusUid(f);
					this.setUidDateTime(systemRoleMenusEntity);
					service.insert(systemRoleMenusEntity);
				}
			}
		}
		JsonResponse result = new JsonResponse();
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0003", new String[] {}));
		this.setResult(result);
	}

	public void updateUserRole() throws Exception {
		HashMap<String, Object> params = this.paserJsonToEntity(HashMap.class);
		String userCode = (String) params.get("userCode");
		List<String> roles = (ArrayList<String>) params.get("roles");
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		SystemUserRoleEntity deleteEntity = new SystemUserRoleEntity();
		deleteEntity.setUserCode(userCode);
		service.delete(deleteEntity);
		for (String r : roles) {
			SystemUserRoleEntity updateEntity = new SystemUserRoleEntity();
			updateEntity.setUserCode(userCode);
			updateEntity.setRoleCode(r);
			this.setUidDateTime(updateEntity, false, true, true);
			service.insert(updateEntity);
		}
		JsonResponse result = new JsonResponse();
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0003", new String[] {}));
		this.setResult(result);
	}

	public void getUserButton() throws Exception {
		FreemarkerResponse result = new FreemarkerResponse("button/setbutton");
		this.setResult(result);
	}

	public void sysMenuSearchMenuTree() throws Exception {
		ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
		List<HashMap<String, Object>> searchList = service.sysMenusearchMenuTree();
		//JSONArray resourcesArray = JSONArray.fromObject(searchList);
		JsonResponse result = new JsonResponse();
		result.put("menuArray", searchList);
		this.setResult(result);
	}

	public void sysMenuSave() throws Exception {
		HashMap<String, Object> params = this.paserJsonToEntity(HashMap.class);
		List<HashMap<String, String>> menu = (List<HashMap<String, String>>) params.get("menuinfo");
		ISearchMenuService service = ApplicationContext.getBean("searchMenuService", ISearchMenuService.class);
		int c = service.batchSaveMenu(menu);
		JsonResponse result = new JsonResponse();
		result.put("count", c);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0003", new String[] {}));
		this.setResult(result);
	}
}