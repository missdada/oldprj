package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessSearchEntity;
import manytag.framework.util.DateUtil;

public class TreedemoSearchEntity extends BaseBusicessSearchEntity {
	private String uid;
	private String parentid;
	private String name;
	private String memo;
	private Integer sort;
	private String sortStr;
	private Integer sortSearchBegin;
	private String sortSearchBeginStr;
	private Integer sortSearchEnd;
	private String sortSearchEndStr;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
		this.sortStr = "" + sort;
	}

	public String getSortStr() {
		return sortStr;
	}

	public void setSortStr(String sortStr) {
		this.sortStr = sortStr;
		this.sort = Integer.parseInt(sortStr);
	}

	public Integer getSortSearchBegin() {
		return sortSearchBegin;
	}

	public void setSortSearchBegin(Integer sortSearchBegin) {
		this.sortSearchBegin = sortSearchBegin;
		this.sortSearchBeginStr = "" + sortSearchBegin;
	}

	public String getSortSearchBeginStr() {
		return sortSearchBeginStr;
	}

	public void setSortSearchBeginStr(String sortSearchBeginStr) {
		this.sortSearchBeginStr = sortSearchBeginStr;
		this.sortSearchBegin = Integer.parseInt(sortSearchBeginStr);
	}

	public Integer getSortSearchEnd() {
		return sortSearchEnd;
	}

	public void setSortSearchEnd(Integer sortSearchEnd) {
		this.sortSearchEnd = sortSearchEnd;
		this.sortSearchEndStr = "" + sortSearchEnd;
	}

	public String getSortSearchEndStr() {
		return sortSearchEndStr;
	}

	public void setSortSearchEndStr(String sortSearchEndStr) {
		this.sortSearchEndStr = sortSearchEndStr;
		this.sortSearchEnd = Integer.parseInt(sortSearchEndStr);
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