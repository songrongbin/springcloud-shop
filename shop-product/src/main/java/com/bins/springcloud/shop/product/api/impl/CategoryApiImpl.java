package com.bins.springcloud.shop.product.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.product.api.CategoryApi;
import com.bins.springcloud.shop.product.service.CategoryService;
import com.bins.springcloud.shop.product.vo.CategoryVo;

@RestController
public class CategoryApiImpl implements CategoryApi {
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public CategoryVo getById(Long id) {
		return categoryService.getById(id);
	}

	@Override
	public List<CategoryVo> getByIds(List<Long> ids) {
		return categoryService.getByIds(ids);
	}

}
