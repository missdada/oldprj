package manytag.common.dao.entity;

import manytag.framework.dispatch.base.BaseEntity;
import manytag.framework.util.DateUtil;

public class SystemMonitorLoginSearchEntity extends BaseEntity {
	private Integer id;
	private String idStr;
	private Integer idSearchBegin;
	private String idSearchBeginStr;
	private Integer idSearchEnd;
	private String idSearchEndStr;
	private String userCode;
	private java.util.Date loginTime;
	private String loginTimeStr;
	private java.util.Date loginTimeSearchBegin;
	private String loginTimeSearchBeginStr;
	private java.util.Date loginTimeSearchEnd;
	private String loginTimeSearchEndStr;
	private String ip;
	private String memo;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.idStr = "" + id;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
		this.id = Integer.parseInt(idStr);
	}

	public Integer getIdSearchBegin() {
		return idSearchBegin;
	}

	public void setIdSearchBegin(Integer idSearchBegin) {
		this.idSearchBegin = idSearchBegin;
		this.idSearchBeginStr = "" + idSearchBegin;
	}

	public String getIdSearchBeginStr() {
		return idSearchBeginStr;
	}

	public void setIdSearchBeginStr(String idSearchBeginStr) {
		this.idSearchBeginStr = idSearchBeginStr;
		this.idSearchBegin = Integer.parseInt(idSearchBeginStr);
	}

	public Integer getIdSearchEnd() {
		return idSearchEnd;
	}

	public void setIdSearchEnd(Integer idSearchEnd) {
		this.idSearchEnd = idSearchEnd;
		this.idSearchEndStr = "" + idSearchEnd;
	}

	public String getIdSearchEndStr() {
		return idSearchEndStr;
	}

	public void setIdSearchEndStr(String idSearchEndStr) {
		this.idSearchEndStr = idSearchEndStr;
		this.idSearchEnd = Integer.parseInt(idSearchEndStr);
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
		this.loginTimeStr = DateUtil.longToDateStr(loginTime.getTime());
	}

	public String getLoginTimeStr() {
		return loginTimeStr;
	}

	public void setLoginTimeStr(String loginTimeStr) {
		this.loginTimeStr = loginTimeStr;
		this.loginTime = new java.util.Date(DateUtil.dateStrToLong(loginTimeStr));
	}

	public java.util.Date getLoginTimeSearchBegin() {
		return loginTimeSearchBegin;
	}

	public void setLoginTimeSearchBegin(java.util.Date loginTimeSearchBegin) {
		this.loginTimeSearchBegin = loginTimeSearchBegin;
		this.loginTimeSearchBeginStr = DateUtil.longToDateStr(loginTimeSearchBegin.getTime());
	}

	public String getLoginTimeSearchBeginStr() {
		return loginTimeSearchBeginStr;
	}

	public void setLoginTimeSearchBeginStr(String loginTimeSearchBeginStr) {
		this.loginTimeSearchBeginStr = loginTimeSearchBeginStr;
		this.loginTimeSearchBegin = new java.util.Date(DateUtil.dateStrToLong(loginTimeSearchBeginStr));
	}

	public java.util.Date getLoginTimeSearchEnd() {
		return loginTimeSearchEnd;
	}

	public void setLoginTimeSearchEnd(java.util.Date loginTimeSearchEnd) {
		this.loginTimeSearchEnd = loginTimeSearchEnd;
		this.loginTimeSearchEndStr = DateUtil.longToDateStr(loginTimeSearchEnd.getTime());
	}

	public String getLoginTimeSearchEndStr() {
		return loginTimeSearchEndStr;
	}

	public void setLoginTimeSearchEndStr(String loginTimeSearchEndStr) {
		this.loginTimeSearchEndStr = loginTimeSearchEndStr;
		this.loginTimeSearchEnd = new java.util.Date(DateUtil.dateStrToLong(loginTimeSearchEndStr));
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