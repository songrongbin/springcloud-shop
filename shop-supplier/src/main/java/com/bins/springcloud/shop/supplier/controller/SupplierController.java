package com.bins.springcloud.shop.supplier.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.supplier.dto.SupplierDto;
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
	
	@GetMapping("/detail")
	public ResultVo<SupplierVo> detail(SupplierDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<SupplierVo>(1, "参数错误!", null);
		}
		ResultVo<SupplierVo> result = supplierService.getDetail(dto);
		return result;
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> edit(@RequestBody SupplierDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		ResultVo<Boolean> result = supplierService.updateSupplier(dto);
		return result;
	}
	
	@RequestMapping("/add")
	public ResultVo<SupplierVo> add(@RequestBody SupplierDto dto) {
		if (StringUtils.isBlank(dto.getSupplierCode())) {
			return new ResultVo<SupplierVo>(ResultCodeEnum.FAILURE.getCode(), "供应商编码不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getSupplierName())) {
			return new ResultVo<SupplierVo>(ResultCodeEnum.FAILURE.getCode(), "供应商名称不能为空!", null);
		}
		return supplierService.addSupplier(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> delete(SupplierDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return supplierService.delSupplier(dto);
	}
}
