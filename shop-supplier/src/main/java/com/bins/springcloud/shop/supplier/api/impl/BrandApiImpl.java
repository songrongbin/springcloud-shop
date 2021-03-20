package com.bins.springcloud.shop.supplier.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bins.springcloud.shop.supplier.api.BrandApi;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;

public class BrandApiImpl implements BrandApi {
	
	@Autowired
	private BrandService brandService;

	@Override
	public BrandVo getById(Long brandId) {
		return brandService.getById(brandId);
	}

	@Override
	public List<BrandVo> getByIds(List<Long> brandIds) {
		return brandService.getByIds(brandIds);
	}

}
