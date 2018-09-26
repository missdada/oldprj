package manytag.framework.dispatch.base;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -7142098522808225797L;

	private int rows;
	private int page;
	private int offset;

	private String uploadfile_uids = "";
	private String uploadfile_urls = "";

	public BaseEntity() {
		rows = 1000;
		page = 1;
		offset = 0;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getUploadfile_uids() {
		return uploadfile_uids;
	}

	public void setUploadfile_uids(String uploadfile_uids) {
		this.uploadfile_uids = uploadfile_uids;
	}

	public String getUploadfile_urls() {
		return uploadfile_urls;
	}

	public void setUploadfile_urls(String uploadfile_urls) {
		this.uploadfile_urls = uploadfile_urls;
	}
}