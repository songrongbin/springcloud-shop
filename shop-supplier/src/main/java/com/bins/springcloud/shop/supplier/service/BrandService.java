package com.bins.springcloud.shop.supplier.service;

import java.util.List;

import com.bins.springcloud.shop.supplier.dto.BrandPageDto;
import com.bins.springcloud.shop.supplier.vo.BrandVo;
import com.github.pagehelper.PageInfo;

public interface BrandService {

	BrandVo getById(Long brandId);

	List<BrandVo> getByIds(List<Long> brandId);

	PageInfo<BrandVo> getPagination(BrandPageDto pageDto);
	
}