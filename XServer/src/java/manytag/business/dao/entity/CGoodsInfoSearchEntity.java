package manytag.business.dao.entity;

import manytag.business.base.BaseBusicessSearchEntity;
import manytag.framework.util.DateUtil;

public class CGoodsInfoSearchEntity extends BaseBusicessSearchEntity {
	private String uid;
	private String goodsNum;
	private String goodsName;
	private String type1;
	private String type2;
	private String type3;
	private Float price;
	private Float discountPrice;
	private String barcode;
	private String manufacturer;
	private String imagesUid;
	private String licenseFileUid;
	private Double weight;
	private String weightStr;
	private Double weightSearchBegin;
	private String weightSearchBeginStr;
	private Double weightSearchEnd;
	private String weightSearchEndStr;
	private String madeIn;
	private java.util.Date madeDate;
	private String madeDateStr;
	private java.util.Date madeDateSearchBegin;
	private String madeDateSearchBeginStr;
	private java.util.Date madeDateSearchEnd;
	private String madeDateSearchEndStr;
	private String deleteFlag;
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

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
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

	public String getImagesUid() {
		return imagesUid;
	}

	public void setImagesUid(String imagesUid) {
		this.imagesUid = imagesUid;
	}

	public String getLicenseFileUid() {
		return licenseFileUid;
	}

	public void setLicenseFileUid(String licenseFileUid) {
		this.licenseFileUid = licenseFileUid;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
		this.weightStr = "" + weight;
	}

	public String getWeightStr() {
		return weightStr;
	}

	public void setWeightStr(String weightStr) {
		this.weightStr = weightStr;
		this.weight = Double.parseDouble(weightStr);
	}

	public Double getWeightSearchBegin() {
		return weightSearchBegin;
	}

	public void setWeightSearchBegin(Double weightSearchBegin) {
		this.weightSearchBegin = weightSearchBegin;
		this.weightSearchBeginStr = "" + weightSearchBegin;
	}

	public String getWeightSearchBeginStr() {
		return weightSearchBeginStr;
	}

	public void setWeightSearchBeginStr(String weightSearchBeginStr) {
		this.weightSearchBeginStr = weightSearchBeginStr;
		this.weightSearchBegin = Double.parseDouble(weightSearchBeginStr);
	}

	public Double getWeightSearchEnd() {
		return weightSearchEnd;
	}

	public void setWeightSearchEnd(Double weightSearchEnd) {
		this.weightSearchEnd = weightSearchEnd;
		this.weightSearchEndStr = "" + weightSearchEnd;
	}

	public String getWeightSearchEndStr() {
		return weightSearchEndStr;
	}

	public void setWeightSearchEndStr(String weightSearchEndStr) {
		this.weightSearchEndStr = weightSearchEndStr;
		this.weightSearchEnd = Double.parseDouble(weightSearchEndStr);
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public java.util.Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(java.util.Date madeDate) {
		this.madeDate = madeDate;
		this.madeDateStr = DateUtil.longToDateStr(madeDate.getTime());
	}

	public String getMadeDateStr() {
		return madeDateStr;
	}

	public void setMadeDateStr(String madeDateStr) {
		this.madeDateStr = madeDateStr;
		this.madeDate = new java.util.Date(DateUtil.dateStrToLong(madeDateStr));
	}

	public java.util.Date getMadeDateSearchBegin() {
		return madeDateSearchBegin;
	}

	public void setMadeDateSearchBegin(java.util.Date madeDateSearchBegin) {
		this.madeDateSearchBegin = madeDateSearchBegin;
		this.madeDateSearchBeginStr = DateUtil.longToDateStr(madeDateSearchBegin.getTime());
	}

	public String getMadeDateSearchBeginStr() {
		return madeDateSearchBeginStr;
	}

	public void setMadeDateSearchBeginStr(String madeDateSearchBeginStr) {
		this.madeDateSearchBeginStr = madeDateSearchBeginStr;
		this.madeDateSearchBegin = new java.util.Date(DateUtil.dateStrToLong(madeDateSearchBeginStr));
	}

	public java.util.Date getMadeDateSearchEnd() {
		return madeDateSearchEnd;
	}

	public void setMadeDateSearchEnd(java.util.Date madeDateSearchEnd) {
		this.madeDateSearchEnd = madeDateSearchEnd;
		this.madeDateSearchEndStr = DateUtil.longToDateStr(madeDateSearchEnd.getTime());
	}

	public String getMadeDateSearchEndStr() {
		return madeDateSearchEndStr;
	}

	public void setMadeDateSearchEndStr(String madeDateSearchEndStr) {
		this.madeDateSearchEndStr = madeDateSearchEndStr;
		this.madeDateSearchEnd = new java.util.Date(DateUtil.dateStrToLong(madeDateSearchEndStr));
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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