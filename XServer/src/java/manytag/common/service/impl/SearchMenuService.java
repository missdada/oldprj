package manytag.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemMenusEntity;
import manytag.common.service.ISearchMenuService;
import manytag.common.service.ISystemMenusService;
import manytag.framework.dao.mybatis.BaseDAO;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.util.StringUtils;

@Service("searchMenuService")
public class SearchMenuService implements ISearchMenuService {
	@Resource
	private BaseDAO baseDAO;

	public List<HashMap<String, Object>> searchMenu() throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.searchMenu");
	}

	public List<HashMap<String, Object>> roleMenuSearch(String role_uid) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.roleMenuSearch", role_uid);
	}

	public List<HashMap<String, Object>> roleMenuActionSearch(String role_uid) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.roleMenuActionSearch", role_uid);
	}

	public List<HashMap<String, Object>> selectUserMenuList(Map<String, List<String>> userMenuList) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.selectUserMenuList", userMenuList);
	}

	public List<HashMap<String, Object>> getParentMenu(String function_id) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.getParentMenu", function_id);
	}

	public void getAllParentMenu(List<HashMap<String, Object>> menulist, HashMap<String, Object> menu) throws Exception {
		if (menu == null || menu.size() == 0) {
			return;
		}

		String menu_parent = (String) menu.get("parent_id");
		if (!"tree".equals(menu_parent)) {
			// 说明还有上级菜单
			// 先从menulist里找父菜单，如果有，直接加入到child里
			HashMap<String, Object> parent = getMenuFromList(menulist, menu_parent);
			if (parent != null) {
				List<HashMap<String, Object>> child = (List<HashMap<String, Object>>) parent.get("child");
				if (child == null) {
					child = new ArrayList<HashMap<String, Object>>();
				}
				child.add(menu);
				parent.put("child", child);
			} else {
				// 从menu菜单表里找到parent
				List<HashMap<String, Object>> parentMenuList = getParentMenu(menu_parent);
				if (parentMenuList != null && parentMenuList.size() > 0) {
					parent = parentMenuList.get(0);
					List<HashMap<String, Object>> child = new ArrayList<HashMap<String, Object>>();
					child.add(menu);
					parent.put("child", child);
					getAllParentMenu(menulist, parent);
				} else {
					// 虽然还有上级菜单，但是没找到,什么也不做
					return;
				}
			}
		} else {
			return;
		}
	}

	private HashMap<String, Object> getMenuFromList(List<HashMap<String, Object>> menulist, String function_id) {
		for (HashMap<String, Object> menu : menulist) {
			String menu_funcid = (String) menu.get("function_id");
			if (!StringUtils.checkBlank(menu_funcid)) {
				if (menu_funcid.equals(function_id)) {
					return menu;
				} else {
					if (menu.get("child") != null) {
						List<HashMap<String, Object>> child = (List<HashMap<String, Object>>) menu.get("child");
						HashMap<String, Object> submenu = getMenuFromList(child, function_id);
						if (submenu != null) {
							return submenu;
						}
					}
				}
			}
		}
		return null;
	}

	public List<HashMap<String, Object>> searchMenuTree() throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.searchMenuTree");
	}

	public List<HashMap<String, Object>> sysMenusearchMenuTree() throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.sysMenusearchMenuTree");
	}

	public int saveMenu(List<HashMap<String, String>> menu) throws Exception {
		ISystemMenusService systemMenusService = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);

		if (menu == null || menu.isEmpty()) {
			return 0;
		}
		int deletec = sysMenudelete();
		SystemMenusEntity systemMenusEntity = null;
		int inc = 0;
		for (HashMap<String, String> m : menu) {
			systemMenusEntity = new SystemMenusEntity();
			systemMenusEntity.setFunctionId(m.get("function_id"));
			systemMenusEntity.setName(m.get("name"));
			systemMenusEntity.setMenuType(m.get("menu_type"));
			systemMenusEntity.setUrl(m.get("url"));
			Integer group = new Integer(m.get("group_by"));
			systemMenusEntity.setGroupBy(group);
			Integer sort = new Integer(m.get("sort_num"));
			systemMenusEntity.setSortNum(sort);
			systemMenusEntity.setParentId(m.get("parent_id"));
			systemMenusEntity.setIcon(m.get("icon"));

			inc += systemMenusService.insert(systemMenusEntity);
			String menuaction = m.get("menu_action");
			if (!StringUtils.checkBlank(menuaction)) {
				systemMenusEntity = new SystemMenusEntity();
				systemMenusEntity.setFunctionId(m.get("function_id") + "_action");
				systemMenusEntity.setName(m.get("name") + "_后台");
				systemMenusEntity.setMenuType("action");
				systemMenusEntity.setUrl(menuaction);
				systemMenusEntity.setGroupBy(Integer.decode(m.get("group_by")));
				systemMenusEntity.setSortNum(Integer.decode(m.get("sort_num")));
				systemMenusEntity.setParentId(m.get("function_id"));
				//systemMenusEntity.setIcon(m.get("icon"));
				systemMenusEntity.setMenu(m.get("menu_actionid"));
				inc += systemMenusService.insert(systemMenusEntity);
			}
		}
		return inc;
	}

	public int batchSaveMenu(List<HashMap<String, String>> menu) throws Exception {
		if (menu == null || menu.isEmpty()) {
			return 0;
		}
		int deletec = sysMenudelete();
		SystemMenusEntity systemMenusEntity = null;
		List<SystemMenusEntity> menuList = new ArrayList<SystemMenusEntity>();

		int inc = 0;
		for (HashMap<String, String> m : menu) {
			systemMenusEntity = new SystemMenusEntity();
			systemMenusEntity.setFunctionId(m.get("function_id"));
			systemMenusEntity.setName(m.get("name"));
			systemMenusEntity.setMenuType(m.get("menu_type"));
			systemMenusEntity.setUrl(m.get("url"));
			Integer group = new Integer(m.get("group_by"));
			systemMenusEntity.setGroupBy(group);
			Integer sort = new Integer(m.get("sort_num"));
			systemMenusEntity.setSortNum(sort);
			systemMenusEntity.setParentId(m.get("parent_id"));
			systemMenusEntity.setIcon(m.get("icon"));

			//inc+=systemMenusService.insert(systemMenusEntity);
			menuList.add(systemMenusEntity);
			String menuaction = m.get("menu_action");
			if (!StringUtils.checkBlank(menuaction)) {
				systemMenusEntity = new SystemMenusEntity();
				systemMenusEntity.setFunctionId(m.get("function_id") + "_action");
				systemMenusEntity.setName(m.get("name") + "_后台");
				systemMenusEntity.setMenuType("action");
				systemMenusEntity.setUrl(menuaction);
				systemMenusEntity.setGroupBy(Integer.decode(m.get("group_by")));
				systemMenusEntity.setSortNum(Integer.decode(m.get("sort_num")));
				systemMenusEntity.setParentId(m.get("function_id"));
				//systemMenusEntity.setIcon(m.get("icon"));
				systemMenusEntity.setMenu(m.get("menu_actionid"));
				//inc+=systemMenusService.insert(systemMenusEntity);
				menuList.add(systemMenusEntity);
				System.out.println(menuList.size());
			}
		}

		baseDAO.batchInsert("manytag.common.dao.entity.mapper.ISystemMenusMapper.insert", menuList);
		//List<HashMap<String, Object>> list = baseDAO.selectList("manytag.business.dao.entity.mapper.ISearchMenuMapper.test");

		return 1;
	}

	public int sysMenudelete() throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ISearchMenuMapper.sysMenudelete");
	}
}