package com.bins.springcloud.common.dto;

public class PageDto {

	private static Integer DEFAULT_PAGESIZE = 10;
	private static Integer DEFAULT_PAGENUM = 1;

	private Integer pageNum;
	private Integer pageSize;

	public Integer getPageNum() {
		if (this.pageSize == null || this.pageSize == 0) {
			return DEFAULT_PAGENUM;
		}
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		if (this.pageSize == null || this.pageSize == 0) {
			return DEFAULT_PAGESIZE;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
