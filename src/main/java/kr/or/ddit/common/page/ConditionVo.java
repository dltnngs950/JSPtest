package kr.or.ddit.common.page;

public class ConditionVo {
	
	private String contents;
	private int page;
	private int pageSize;
	
	public ConditionVo() {}
	
	public ConditionVo(String contents, int page, int pageSize) {
		this.contents = contents;
		this.page = page;
		this.pageSize = pageSize;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	

}
