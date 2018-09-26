package manytag.common.dao.entity;

import manytag.framework.dispatch.base.BaseEntity;
import manytag.framework.util.DateUtil;

public class SystemMonitorOperationSearchEntity extends BaseEntity {
	private Integer responseTime;
	private String responseTimeStr;
	private Integer responseTimeSearchBegin;
	private String responseTimeSearchBeginStr;
	private Integer responseTimeSearchEnd;
	private String responseTimeSearchEndStr;
	private Integer id;
	private String idStr;
	private Integer idSearchBegin;
	private String idSearchBeginStr;
	private Integer idSearchEnd;
	private String idSearchEndStr;
	private String userCode;
	private String modelContent;
	private String functionContent;
	private String ip;
	private String operationResult;
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

	public Integer getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
		this.responseTimeStr = "" + responseTime;
	}

	public String getResponseTimeStr() {
		return responseTimeStr;
	}

	public void setResponseTimeStr(String responseTimeStr) {
		this.responseTimeStr = responseTimeStr;
		this.responseTime = Integer.parseInt(responseTimeStr);
	}

	public Integer getResponseTimeSearchBegin() {
		return responseTimeSearchBegin;
	}

	public void setResponseTimeSearchBegin(Integer responseTimeSearchBegin) {
		this.responseTimeSearchBegin = responseTimeSearchBegin;
		this.responseTimeSearchBeginStr = "" + responseTimeSearchBegin;
	}

	public String getResponseTimeSearchBeginStr() {
		return responseTimeSearchBeginStr;
	}

	public void setResponseTimeSearchBeginStr(String responseTimeSearchBeginStr) {
		this.responseTimeSearchBeginStr = responseTimeSearchBeginStr;
		this.responseTimeSearchBegin = Integer.parseInt(responseTimeSearchBeginStr);
	}

	public Integer getResponseTimeSearchEnd() {
		return responseTimeSearchEnd;
	}

	public void setResponseTimeSearchEnd(Integer responseTimeSearchEnd) {
		this.responseTimeSearchEnd = responseTimeSearchEnd;
		this.responseTimeSearchEndStr = "" + responseTimeSearchEnd;
	}

	public String getResponseTimeSearchEndStr() {
		return responseTimeSearchEndStr;
	}

	public void setResponseTimeSearchEndStr(String responseTimeSearchEndStr) {
		this.responseTimeSearchEndStr = responseTimeSearchEndStr;
		this.responseTimeSearchEnd = Integer.parseInt(responseTimeSearchEndStr);
	}

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

	public String getModelContent() {
		return modelContent;
	}

	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}

	public String getFunctionContent() {
		return functionContent;
	}

	public void setFunctionContent(String functionContent) {
		this.functionContent = functionContent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
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