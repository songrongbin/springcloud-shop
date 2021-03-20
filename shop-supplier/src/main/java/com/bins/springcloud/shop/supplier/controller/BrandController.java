package com.bins.springcloud.shop.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.supplier.dto.BrandPageDto;
import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.service.SupplierService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Autowired
	private BrandService brandService;

	@GetMapping("/pagination")
	public ResultVo<PageInfo<BrandVo>> pageList(BrandPageDto pageDto) {
		PageInfo<BrandVo> pageInfo = brandService.getPagination(pageDto);
		ResultVo<PageInfo<BrandVo>> result = new ResultVo<PageInfo<BrandVo>>();
		result.setData(pageInfo);
		return result;
	}
}
