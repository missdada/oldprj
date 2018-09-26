package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessEntity;
import manytag.framework.util.DateUtil;

public class LicenseInfoEntity extends BaseBusicessEntity {
	private String uid;
	private String licenseTrialFlag;
	private String licenseTrialFlagStr;
	private String productName;
	private String productNameStr;
	private String productVersion;
	private String productVersionStr;
	private String mac;
	private String customerName;
	private String contractImagesUid;
	private String contractImagesUidStr;
	private String requestSheetFileUid;
	private String requestSheetFileUidStr;
	private Integer installValidDate;
	private Integer trialDays;
	private String trialDaysStr;
	private String licenseCode;
	private String memo;
	private java.util.Date createTime;
	private String createTimeStr;
	private String createUser;
	private java.util.Date updateTime;
	private String updateTimeStr;
	private String updateUser;
	private String isEffect;

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

	public String getLicenseTrialFlagStr() {
		return licenseTrialFlagStr;
	}

	public void setLicenseTrialFlagStr(String licenseTrialFlagStr) {
		this.licenseTrialFlagStr = licenseTrialFlagStr;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameStr() {
		return productNameStr;
	}

	public void setProductNameStr(String productNameStr) {
		this.productNameStr = productNameStr;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getProductVersionStr() {
		return productVersionStr;
	}

	public void setProductVersionStr(String productVersionStr) {
		this.productVersionStr = productVersionStr;
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

	public String getContractImagesUidStr() {
		return contractImagesUidStr;
	}

	public void setContractImagesUidStr(String contractImagesUidStr) {
		this.contractImagesUidStr = contractImagesUidStr;
	}

	public String getRequestSheetFileUid() {
		return requestSheetFileUid;
	}

	public void setRequestSheetFileUid(String requestSheetFileUid) {
		this.requestSheetFileUid = requestSheetFileUid;
	}

	public String getRequestSheetFileUidStr() {
		return requestSheetFileUidStr;
	}

	public void setRequestSheetFileUidStr(String requestSheetFileUidStr) {
		this.requestSheetFileUidStr = requestSheetFileUidStr;
	}

	public Integer getInstallValidDate() {
		return installValidDate;
	}

	public void setInstallValidDate(Integer installValidDate) {
		this.installValidDate = installValidDate;
	}

	public Integer getTrialDays() {
		return trialDays;
	}

	public void setTrialDays(Integer trialDays) {
		this.trialDays = trialDays;
	}

	public String getTrialDaysStr() {
		return trialDaysStr;
	}

	public void setTrialDaysStr(String trialDaysStr) {
		this.trialDaysStr = trialDaysStr;
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

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}
}