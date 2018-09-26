package manytag.common.dao.entity;

import manytag.framework.dispatch.base.BaseEntity;
import manytag.framework.util.DateUtil;

public class SystemMonitorLoginEntity extends BaseEntity {
	private Integer id;
	private String userCode;
	private java.util.Date loginTime;
	private String loginTimeStr;
	private String ip;
	private String memo;
	private java.util.Date createTime;
	private String createTimeStr;
	private String createUser;
	private java.util.Date updateTime;
	private String updateTimeStr;
	private String updateUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
		if (loginTime != null) {
			this.loginTimeStr = DateUtil.longToDateStr(loginTime.getTime());
		}
	}

	public String getLoginTimeStr() {
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
		if (loginTimeStr != null && !"".equals(loginTimeStr)) {
			this.loginTime = new java.util.Date(DateUtil.dateStrToLong(loginTimeStr));
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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