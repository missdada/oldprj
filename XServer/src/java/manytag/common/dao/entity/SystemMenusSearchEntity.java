package manytag.common.dao.entity;

import manytag.framework.dispatch.base.BaseEntity;
import manytag.framework.util.DateUtil;

public class SystemMenusSearchEntity extends BaseEntity {
	private String functionId;
	private String name;
	private String menuType;
	private String url;
	private Integer groupBy;
	private String groupByStr;
	private Integer groupBySearchBegin;
	private String groupBySearchBeginStr;
	private Integer groupBySearchEnd;
	private String groupBySearchEndStr;
	private Integer sortNum;
	private String sortNumStr;
	private Integer sortNumSearchBegin;
	private String sortNumSearchBeginStr;
	private Integer sortNumSearchEnd;
	private String sortNumSearchEndStr;
	private String parentId;
	private String icon;
	private String memo;
	private String menu;
	private java.util.Date createTime;
	private String createTimeStr;
	private java.util.Date createTimeSearchBegin;
	private String createTimeSearchBeginStr;
	private java.util.Date createTimeSearchEnd;
	private String createTimeSearchEndStr;
	private String createUser;
	private java.util.Date updateTime;
	private String updateTimeStr;
	private java.util.Date updateTimeSearchBegin;
	private String updateTimeSearchBeginStr;
	private java.util.Date updateTimeSearchEnd;
	private String updateTimeSearchEndStr;
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
		this.groupByStr = "" + groupBy;
	}

	public String getGroupByStr() {
		return groupByStr;
	}

	public void setGroupByStr(String groupByStr) {
		this.groupByStr = groupByStr;
		this.groupBy = Integer.parseInt(groupByStr);
	}

	public Integer getGroupBySearchBegin() {
		return groupBySearchBegin;
	}

	public void setGroupBySearchBegin(Integer groupBySearchBegin) {
		this.groupBySearchBegin = groupBySearchBegin;
		this.groupBySearchBeginStr = "" + groupBySearchBegin;
	}

	public String getGroupBySearchBeginStr() {
		return groupBySearchBeginStr;
	}

	public void setGroupBySearchBeginStr(String groupBySearchBeginStr) {
		this.groupBySearchBeginStr = groupBySearchBeginStr;
		this.groupBySearchBegin = Integer.parseInt(groupBySearchBeginStr);
	}

	public Integer getGroupBySearchEnd() {
		return groupBySearchEnd;
	}

	public void setGroupBySearchEnd(Integer groupBySearchEnd) {
		this.groupBySearchEnd = groupBySearchEnd;
		this.groupBySearchEndStr = "" + groupBySearchEnd;
	}

	public String getGroupBySearchEndStr() {
		return groupBySearchEndStr;
	}

	public void setGroupBySearchEndStr(String groupBySearchEndStr) {
		this.groupBySearchEndStr = groupBySearchEndStr;
		this.groupBySearchEnd = Integer.parseInt(groupBySearchEndStr);
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
		this.sortNumStr = "" + sortNum;
	}

	public String getSortNumStr() {
		return sortNumStr;
	}

	public void setSortNumStr(String sortNumStr) {
		this.sortNumStr = sortNumStr;
		this.sortNum = Integer.parseInt(sortNumStr);
	}

	public Integer getSortNumSearchBegin() {
		return sortNumSearchBegin;
	}

	public void setSortNumSearchBegin(Integer sortNumSearchBegin) {
		this.sortNumSearchBegin = sortNumSearchBegin;
		this.sortNumSearchBeginStr = "" + sortNumSearchBegin;
	}

	public String getSortNumSearchBeginStr() {
		return sortNumSearchBeginStr;
	}

	public void setSortNumSearchBeginStr(String sortNumSearchBeginStr) {
		this.sortNumSearchBeginStr = sortNumSearchBeginStr;
		this.sortNumSearchBegin = Integer.parseInt(sortNumSearchBeginStr);
	}

	public Integer getSortNumSearchEnd() {
		return sortNumSearchEnd;
	}

	public void setSortNumSearchEnd(Integer sortNumSearchEnd) {
		this.sortNumSearchEnd = sortNumSearchEnd;
		this.sortNumSearchEndStr = "" + sortNumSearchEnd;
	}

	public String getSortNumSearchEndStr() {
		return sortNumSearchEndStr;
	}

	public void setSortNumSearchEndStr(String sortNumSearchEndStr) {
		this.sortNumSearchEndStr = sortNumSearchEndStr;
		this.sortNumSearchEnd = Integer.parseInt(sortNumSearchEndStr);
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
		this.createTimeStr = DateUtil.longToDateStr(createTime.getTime());
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime = new java.util.Date(DateUtil.dateStrToLong(createTimeStr));
	}

	public java.util.Date getCreateTimeSearchBegin() {
		return createTimeSearchBegin;
	}

	public void setCreateTimeSearchBegin(java.util.Date createTimeSearchBegin) {
		this.createTimeSearchBegin = createTimeSearchBegin;
		this.createTimeSearchBeginStr = DateUtil.longToDateStr(createTimeSearchBegin.getTime());
	}

	public String getCreateTimeSearchBeginStr() {
		return createTimeSearchBeginStr;
	}

	public void setCreateTimeSearchBeginStr(String createTimeSearchBeginStr) {
		this.createTimeSearchBeginStr = createTimeSearchBeginStr;
		this.createTimeSearchBegin = new java.util.Date(DateUtil.dateStrToLong(createTimeSearchBeginStr));
	}

	public java.util.Date getCreateTimeSearchEnd() {
		return createTimeSearchEnd;
	}

	public void setCreateTimeSearchEnd(java.util.Date createTimeSearchEnd) {
		this.createTimeSearchEnd = createTimeSearchEnd;
		this.createTimeSearchEndStr = DateUtil.longToDateStr(createTimeSearchEnd.getTime());
	}

	public String getCreateTimeSearchEndStr() {
		return createTimeSearchEndStr;
	}

	public void setCreateTimeSearchEndStr(String createTimeSearchEndStr) {
		this.createTimeSearchEndStr = createTimeSearchEndStr;
		this.createTimeSearchEnd = new java.util.Date(DateUtil.dateStrToLong(createTimeSearchEndStr));
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
		this.updateTimeStr = DateUtil.longToDateStr(updateTime.getTime());
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = new java.util.Date(DateUtil.dateStrToLong(updateTimeStr));
	}

	public java.util.Date getUpdateTimeSearchBegin() {
		return updateTimeSearchBegin;
	}

	public void setUpdateTimeSearchBegin(java.util.Date updateTimeSearchBegin) {
		this.updateTimeSearchBegin = updateTimeSearchBegin;
		this.updateTimeSearchBeginStr = DateUtil.longToDateStr(updateTimeSearchBegin.getTime());
	}

	public String getUpdateTimeSearchBeginStr() {
		return updateTimeSearchBeginStr;
	}

	public void setUpdateTimeSearchBeginStr(String updateTimeSearchBeginStr) {
		this.updateTimeSearchBeginStr = updateTimeSearchBeginStr;
		this.updateTimeSearchBegin = new java.util.Date(DateUtil.dateStrToLong(updateTimeSearchBeginStr));
	}

	public java.util.Date getUpdateTimeSearchEnd() {
		return updateTimeSearchEnd;
	}

	public void setUpdateTimeSearchEnd(java.util.Date updateTimeSearchEnd) {
		this.updateTimeSearchEnd = updateTimeSearchEnd;
		this.updateTimeSearchEndStr = DateUtil.longToDateStr(updateTimeSearchEnd.getTime());
	}

	public String getUpdateTimeSearchEndStr() {
		return updateTimeSearchEndStr;
	}

	public void setUpdateTimeSearchEndStr(String updateTimeSearchEndStr) {
		this.updateTimeSearchEndStr = updateTimeSearchEndStr;
		this.updateTimeSearchEnd = new java.util.Date(DateUtil.dateStrToLong(updateTimeSearchEndStr));
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}