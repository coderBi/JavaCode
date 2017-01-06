package cn.itcast.domain;

public class PageResultSet {
	private boolean showFirstPage;
	private boolean showLastPage;
	private boolean showGoto;
	private Object resultSet;
	private int pageNum;
	private int totalPage;

	public PageResultSet() {

	}

	public PageResultSet(boolean showFirstPage, boolean showLastPage,
			boolean showGoto, Object resultSet, int pageNum, int totalPage) {
		this.showFirstPage = showFirstPage;
		this.showLastPage = showLastPage;
		this.showGoto = showGoto;
		this.resultSet = resultSet;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		String str = new String();
		str += "[PageResultSet:showFirstPage=" + showFirstPage
				+ " showLastPage=" + showLastPage + " showGoto=" + showGoto
				+ " resultSet=" + resultSet + " pageNum=" + pageNum
				+ " totalPage=" + totalPage + "]";
		return str;
	}

	public boolean isShowFirstPage() {
		return showFirstPage;
	}

	public void setShowFirstPage(boolean showFirstPage) {
		this.showFirstPage = showFirstPage;
	}

	public boolean isShowLastPage() {
		return showLastPage;
	}

	public void setShowLastPage(boolean showLastPage) {
		this.showLastPage = showLastPage;
	}

	public boolean isShowGoto() {
		return showGoto;
	}

	public void setShowGoto(boolean showGoto) {
		this.showGoto = showGoto;
	}

	public Object getResultSet() {
		return resultSet;
	}

	public void setResultSet(Object resultSet) {
		this.resultSet = resultSet;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
