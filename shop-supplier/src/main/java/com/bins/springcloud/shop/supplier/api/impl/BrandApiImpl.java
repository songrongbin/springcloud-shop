package com.bins.springcloud.shop.supplier.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.supplier.api.BrandApi;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;

@RestController
public class BrandApiImpl implements BrandApi {
	
	@Autowired
	private BrandService brandService;

	@Override
	public BrandVo getBrandById(Long brandId) {
		return brandService.getById(brandId);
	}

	@Override
	public List<BrandVo> getBrandByIds(List<Long> brandIds) {
		return brandService.getByIds(brandIds);
	}

}
