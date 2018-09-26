package manytag.easytools.data;

public class TableExtendInfo {
	// 页面类型
	private String t;
	/**
	 * 父ID列名定义
	 */
	private String p;
	/**
	 * 排序列定义
	 */
	private String s;
	private String uidColumn;
	/**
	 * 名称显示列定义
	 */
	private String n;

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getUidColumn() {
		return uidColumn;
	}

	public void setUidColumn(String uidColumn) {
		this.uidColumn = uidColumn;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}
}