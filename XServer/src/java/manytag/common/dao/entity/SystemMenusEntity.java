package manytag.common.dao.entity;

import manytag.framework.dispatch.base.BaseEntity;
import manytag.framework.util.DateUtil;

public class SystemMenusEntity extends BaseEntity {
	private String functionId;
	private String name;
	private String menuType;
	private String url;
	private Integer groupBy;
	private Integer sortNum;
	private String parentId;
	private String icon;
	private String memo;
	private String menu;
	private java.util.Date createTime;
	private String createTimeStr;
	private String createUser;
	private java.util.Date updateTime;
	private String updateTimeStr;
	private String updateUser;

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(Integer groupBy) {
		this.groupBy = groupBy;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
		if (createTime != null) {
			this.createTimeStr = DateUtil.longToDateStr(createTime.getTime());
		}
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		if (createTimeStr != null && !"".equals(createTimeStr)) {
			this.createTime = new java.util.Date(DateUtil.dateStrToLong(createTimeStr));
		}
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
		if (updateTime != null) {
			this.updateTimeStr = DateUtil.longToDateStr(updateTime.getTime());
		}
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		if (updateTimeStr != null && !"".equals(updateTimeStr)) {
			this.updateTime = new java.util.Date(DateUtil.dateStrToLong(updateTimeStr));
		}
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}