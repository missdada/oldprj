package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessSearchEntity;
import manytag.framework.util.DateUtil;

public class LicenseInfoSearchEntity extends BaseBusicessSearchEntity {
	private String uid;
	private String licenseTrialFlag;
	private String productName;
	private String productVersion;
	private String mac;
	private String customerName;
	private String contractImagesUid;
	private String requestSheetFileUid;
	private Integer installValidDate;
	private String installValidDateStr;
	private Integer installValidDateSearchBegin;
	private String installValidDateSearchBeginStr;
	private Integer installValidDateSearchEnd;
	private String installValidDateSearchEndStr;
	private Integer trialDays;
	private String trialDaysStr;
	private Integer trialDaysSearchBegin;
	private String trialDaysSearchBeginStr;
	private Integer trialDaysSearchEnd;
	private String trialDaysSearchEndStr;
	private String licenseCode;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLicenseTrialFlag() {
		return licenseTrialFlag;
	}

	public void setLicenseTrialFlag(String licenseTrialFlag) {
		this.licenseTrialFlag = licenseTrialFlag;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContractImagesUid() {
		return contractImagesUid;
	}

	public void setContractImagesUid(String contractImagesUid) {
		this.contractImagesUid = contractImagesUid;
	}

	public String getRequestSheetFileUid() {
		return requestSheetFileUid;
	}

	public void setRequestSheetFileUid(String requestSheetFileUid) {
		this.requestSheetFileUid = requestSheetFileUid;
	}

	public Integer getInstallValidDate() {
		return installValidDate;
	}

	public void setInstallValidDate(Integer installValidDate) {
		this.installValidDate = installValidDate;
		this.installValidDateStr = "" + installValidDate;
	}

	public String getInstallValidDateStr() {
		return installValidDateStr;
	}

	public void setInstallValidDateStr(String installValidDateStr) {
		this.installValidDateStr = installValidDateStr;
		this.installValidDate = Integer.parseInt(installValidDateStr);
	}

	public Integer getInstallValidDateSearchBegin() {
		return installValidDateSearchBegin;
	}

	public void setInstallValidDateSearchBegin(Integer installValidDateSearchBegin) {
		this.installValidDateSearchBegin = installValidDateSearchBegin;
		this.installValidDateSearchBeginStr = "" + installValidDateSearchBegin;
	}

	public String getInstallValidDateSearchBeginStr() {
		return installValidDateSearchBeginStr;
	}

	public void setInstallValidDateSearchBeginStr(String installValidDateSearchBeginStr) {
		this.installValidDateSearchBeginStr = installValidDateSearchBeginStr;
		this.installValidDateSearchBegin = Integer.parseInt(installValidDateSearchBeginStr);
	}

	public Integer getInstallValidDateSearchEnd() {
		return installValidDateSearchEnd;
	}

	public void setInstallValidDateSearchEnd(Integer installValidDateSearchEnd) {
		this.installValidDateSearchEnd = installValidDateSearchEnd;
		this.installValidDateSearchEndStr = "" + installValidDateSearchEnd;
	}

	public String getInstallValidDateSearchEndStr() {
		return installValidDateSearchEndStr;
	}

	public void setInstallValidDateSearchEndStr(String installValidDateSearchEndStr) {
		this.installValidDateSearchEndStr = installValidDateSearchEndStr;
		this.installValidDateSearchEnd = Integer.parseInt(installValidDateSearchEndStr);
	}

	public Integer getTrialDays() {
		return trialDays;
	}

	public void setTrialDays(Integer trialDays) {
		this.trialDays = trialDays;
		this.trialDaysStr = "" + trialDays;
	}

	public String getTrialDaysStr() {
		return trialDaysStr;
	}

	public void setTrialDaysStr(String trialDaysStr) {
		this.trialDaysStr = trialDaysStr;
		this.trialDays = Integer.parseInt(trialDaysStr);
	}

	public Integer getTrialDaysSearchBegin() {
		return trialDaysSearchBegin;
	}

	public void setTrialDaysSearchBegin(Integer trialDaysSearchBegin) {
		this.trialDaysSearchBegin = trialDaysSearchBegin;
		this.trialDaysSearchBeginStr = "" + trialDaysSearchBegin;
	}

	public String getTrialDaysSearchBeginStr() {
		return trialDaysSearchBeginStr;
	}

	public void setTrialDaysSearchBeginStr(String trialDaysSearchBeginStr) {
		this.trialDaysSearchBeginStr = trialDaysSearchBeginStr;
		this.trialDaysSearchBegin = Integer.parseInt(trialDaysSearchBeginStr);
	}

	public Integer getTrialDaysSearchEnd() {
		return trialDaysSearchEnd;
	}

	public void setTrialDaysSearchEnd(Integer trialDaysSearchEnd) {
		this.trialDaysSearchEnd = trialDaysSearchEnd;
		this.trialDaysSearchEndStr = "" + trialDaysSearchEnd;
	}

	public String getTrialDaysSearchEndStr() {
		return trialDaysSearchEndStr;
	}

	public void setTrialDaysSearchEndStr(String trialDaysSearchEndStr) {
		this.trialDaysSearchEndStr = trialDaysSearchEndStr;
		this.trialDaysSearchEnd = Integer.parseInt(trialDaysSearchEndStr);
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
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