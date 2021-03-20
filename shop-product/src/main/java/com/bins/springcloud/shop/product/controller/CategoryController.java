package com.bins.springcloud.shop.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.product.dto.CategoryPageDto;
import com.bins.springcloud.shop.product.service.CategoryService;
import com.bins.springcloud.shop.product.vo.CategoryVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/pagination")
	public ResultVo<PageInfo<CategoryVo>> pageList(CategoryPageDto pageDto) {
		PageInfo<CategoryVo> pageInfo = categoryService.getPagination(pageDto);
		ResultVo<PageInfo<CategoryVo>> result = new ResultVo<PageInfo<CategoryVo>>();
		result.setData(pageInfo);
		return result;
	}

}
