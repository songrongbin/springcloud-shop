package com.bins.springcloud.shop.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.service.SupplierService;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/pagination")
	public ResultVo<PageInfo<SupplierVo>> pageList(SupplierPageDto pageDto) {
		PageInfo<SupplierVo> pageInfo = supplierService.getSupplierPagination(pageDto);
		ResultVo<PageInfo<SupplierVo>> result = new ResultVo<PageInfo<SupplierVo>>();
		result.setData(pageInfo);
		return result;
	}
}
