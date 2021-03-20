package com.bins.springcloud.shop.product.service;

import java.util.List;

import com.bins.springcloud.shop.product.dto.CategoryPageDto;
import com.bins.springcloud.shop.product.vo.CategoryVo;
import com.github.pagehelper.PageInfo;

public interface CategoryService {

	CategoryVo getById(Long id);

	List<CategoryVo> getByIds(List<Long> id);

	PageInfo<CategoryVo> getPagination(CategoryPageDto pageDto);

}
