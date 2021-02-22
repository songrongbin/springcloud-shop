package com.bins.springcloud.shop.common.utils;

import com.github.pagehelper.PageInfo;

public class PageUtil {
	
	public static <T, P> PageInfo<T> pageInfoToPageInfoVo(PageInfo<P> originPageInfo) {
		PageInfo<T> pageInfo = new PageInfo<T>();
		pageInfo.setTotal(originPageInfo.getTotal());
		pageInfo.setSize(originPageInfo.getSize());
		pageInfo.setPageSize(originPageInfo.getPageSize());
		pageInfo.setPageNum(originPageInfo.getPageNum());
		pageInfo.setPages(originPageInfo.getPages());
		pageInfo.setStartRow(originPageInfo.getStartRow());
		pageInfo.setEndRow(originPageInfo.getEndRow());
		pageInfo.setEndRow(originPageInfo.getEndRow());
		pageInfo.setPrePage(originPageInfo.getPrePage());
		pageInfo.setNextPage(originPageInfo.getNextPage());
		pageInfo.setHasNextPage(originPageInfo.isHasNextPage());
		pageInfo.setHasPreviousPage(originPageInfo.isHasPreviousPage());
		pageInfo.setIsFirstPage(originPageInfo.isIsFirstPage());
		pageInfo.setIsLastPage(originPageInfo.isIsLastPage());
		pageInfo.setNavigateFirstPage(originPageInfo.getNavigateFirstPage());
		pageInfo.setNavigateLastPage(originPageInfo.getNavigateLastPage());
		pageInfo.setNavigatepageNums(originPageInfo.getNavigatepageNums());
		pageInfo.setNavigatePages(originPageInfo.getNavigatePages());
		return pageInfo;
	}

}