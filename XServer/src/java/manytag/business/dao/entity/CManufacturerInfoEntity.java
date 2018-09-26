package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessEntity;
import manytag.framework.util.DateUtil;

public class CManufacturerInfoEntity extends BaseBusicessEntity {
	private Integer id;
	private String code;
	private String name;
	private String businessScope;
	private String address;
	private String contact;
	private String contactPhone;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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