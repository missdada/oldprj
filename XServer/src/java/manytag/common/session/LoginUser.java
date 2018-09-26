package manytag.common.session;

import manytag.common.dao.entity.SystemUsersEntity;
import manytag.framework.authz.user.AuthzUser;

public class LoginUser extends AuthzUser {
	private static final long serialVersionUID = 7544060097971690079L;

	SystemUsersEntity userInfo;

	private String menus = null;

	public SystemUsersEntity getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SystemUsersEntity userInfo) {
		this.userInfo = userInfo;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String m) {
		this.menus = m;
	}

	public void addMenus(String m) {
		if (menus == null || "".equals(menus)) {
			menus = m;
		} else {
			menus += "," + m;
		}
	}
}