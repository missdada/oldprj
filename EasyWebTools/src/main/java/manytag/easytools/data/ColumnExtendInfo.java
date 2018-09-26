package manytag.easytools.data;

public class ColumnExtendInfo {

	/**
	 * 表控件,html页面的控件类型
	 */
	private String realTypeName;
	/**
	 * 是否外键
	 */
	private String constraint;
	/**
	 * 外键类型
	 */
	private String referenceType;
	/**
	 * 外键表
	 */
	private String referenceTable;
	/**
	 * 外键key
	 */
	private String referenceKey;
	/**
	 * 外键显示的属性
	 */
	private String referenceName;
	private String condition;

	/**
	 * 是否隐藏列表页中的列和编辑页
	 */
	private String hide;
	/**
	 * 是否在编辑html页面为多行控件(textarea)
	 */
	private String multi;
	/**
	 * 列表页检索项
	 */
	private String searchFlag;
	/**
	 * 列表也内容超出(20个字符)时自动截断,并增加tip提示
	 */
	private String subContent;
	/**
	 * 属性是否唯一，不允许重复值
	 */
	private String isOnly;
	/**
	 * TODO 暂未实现<br>
	 * 排序项 值：asc、desc，默认不排序
	 */
	private String sort;
	/**
	 * TODO 暂未实现<br>
	 * 排序级别，值小的在order 不要靠前面，默认随机
	 */
	private int sortIndex;

	public String getRealTypeName() {
		return realTypeName;
	}

	public void setRealTypeName(String realTypeName) {
		this.realTypeName = realTypeName;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceTable() {
		return referenceTable;
	}

	public void setReferenceTable(String referenceTable) {
		this.referenceTable = referenceTable;
	}

	public String getReferenceKey() {
		return referenceKey;
	}

	public void setReferenceKey(String referenceKey) {
		this.referenceKey = referenceKey;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}

	public String getMulti() {
		return multi;
	}

	public void setMulti(String multi) {
		this.multi = multi;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public String getIsOnly() {
		return isOnly;
	}

	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	@Override
	public String toString() {
		String str = "realTypeName=" + realTypeName + ";constraint=" + constraint + ";referenceType=" + referenceType + ";referenceTable="
				+ referenceTable + ";referenceKey=" + referenceKey + ";referenceName=" + referenceName + ";condition=" + condition + ";hide=" + hide
				+ ";multi=" + multi + ";searchFlag=" + searchFlag + ";subContent=" + subContent;
		return str;
	}
}