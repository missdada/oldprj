package manytag.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISearchMenuService {
	public List<HashMap<String, Object>> searchMenu() throws Exception;

	public List<HashMap<String, Object>> searchMenuTree() throws Exception;

	public List<HashMap<String, Object>> sysMenusearchMenuTree() throws Exception;

	public List<HashMap<String, Object>> roleMenuSearch(String role_uid) throws Exception;

	public List<HashMap<String, Object>> roleMenuActionSearch(String role_uid) throws Exception;

	public List<HashMap<String, Object>> getParentMenu(String function_id) throws Exception;

	public List<HashMap<String, Object>> selectUserMenuList(Map<String, List<String>> userMenuList) throws Exception;

	public void getAllParentMenu(List<HashMap<String, Object>> menulist, HashMap<String, Object> menu) throws Exception;

	public int sysMenudelete() throws Exception;

	public int saveMenu(List<HashMap<String, String>> menu) throws Exception;

	public int batchSaveMenu(List<HashMap<String, String>> menu) throws Exception;
}