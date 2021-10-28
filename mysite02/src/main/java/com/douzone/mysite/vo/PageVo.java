package com.douzone.mysite.vo;

public class PageVo {
	private int page;
	private int list_size;
	private int page_size;
	private int totalcnt;
	private int pagecnt;
	private int blockcnt;
	private int currentblock;
	private int beginPage;
	private int prevPage;
	private int nextPage;
	private int endPage;
	private int stindex;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getList_size() {
		return list_size;
	}
	public void setList_size(int list_size) {
		this.list_size = list_size;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getTotalcnt() {
		return totalcnt;
	}
	public void setTotalcnt(int totalcnt) {
		this.totalcnt = totalcnt;
	}
	public int getPagecnt() {
		return pagecnt;
	}
	public void setPagecnt(int pagecnt) {
		this.pagecnt = pagecnt;
	}
	public int getBlockcnt() {
		return blockcnt;
	}
	public void setBlockcnt(int blockcnt) {
		this.blockcnt = blockcnt;
	}
	public int getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(int currentblock) {
		this.currentblock = currentblock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStindex() {
		return stindex;
	}
	public void setStindex(int stindex) {
		this.stindex = stindex;
	}
	
}
