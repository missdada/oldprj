package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessEntity;
import manytag.framework.util.DateUtil;

public class CGoodsInfoEntity extends BaseBusicessEntity {
	private String uid;
	private String goodsNum;
	private String goodsName;
	private String type1;
	private String type1Str;
	private String type2;
	private String type2Str;
	private String type3;
	private String type3Str;
	private Float price;
	private Float discountPrice;
	private String barcode;
	private String manufacturer;
	private String manufacturerStr;
	private String imagesUid;
	private String imagesUidStr;
	private String licenseFileUid;
	private String licenseFileUidStr;
	private Double weight;
	private String madeIn;
	private String madeInStr;
	private java.util.Date madeDate;
	private String madeDateStr;
	private String deleteFlag;
	private String deleteFlagStr;
	private String memo;
	private java.util.Date createTime;
	private String createTimeStr;
	private String createUser;
	private java.util.Date updateTime;
	private String updateTimeStr;
	private String updateUser;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType1Str() {
		return type1Str;
	}

	public void setType1Str(String type1Str) {
		this.type1Str = type1Str;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType2Str() {
		return type2Str;
	}

	public void setType2Str(String type2Str) {
		this.type2Str = type2Str;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getType3Str() {
		return type3Str;
	}

	public void setType3Str(String type3Str) {
		this.type3Str = type3Str;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturerStr() {
		return manufacturerStr;
	}

	public void setManufacturerStr(String manufacturerStr) {
		this.manufacturerStr = manufacturerStr;
	}

	public String getImagesUid() {
		return imagesUid;
	}

	public void setImagesUid(String imagesUid) {
		this.imagesUid = imagesUid;
	}

	public String getImagesUidStr() {
		return imagesUidStr;
	}

	public void setImagesUidStr(String imagesUidStr) {
		this.imagesUidStr = imagesUidStr;
	}

	public String getLicenseFileUid() {
		return licenseFileUid;
	}

	public void setLicenseFileUid(String licenseFileUid) {
		this.licenseFileUid = licenseFileUid;
	}

	public String getLicenseFileUidStr() {
		return licenseFileUidStr;
	}

	public void setLicenseFileUidStr(String licenseFileUidStr) {
		this.licenseFileUidStr = licenseFileUidStr;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getMadeInStr() {
		return madeInStr;
	}

	public void setMadeInStr(String madeInStr) {
		this.madeInStr = madeInStr;
	}

	public java.util.Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(java.util.Date madeDate) {
		this.madeDate = madeDate;
		if (madeDate != null) {
			this.madeDateStr = DateUtil.longToDateStr(madeDate.getTime());
		}
	}

	public String getMadeDateStr() {
		return madeDateStr;
	}

	public void setMadeDateStr(String madeDateStr) {
		this.madeDateStr = madeDateStr;
		if (madeDateStr != null && !"".equals(madeDateStr)) {
			this.madeDate = new java.util.Date(DateUtil.dateStrToLong(madeDateStr));
		}
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getDeleteFlagStr() {
		return deleteFlagStr;
	}

	public void setDeleteFlagStr(String deleteFlagStr) {
		this.deleteFlagStr = deleteFlagStr;
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