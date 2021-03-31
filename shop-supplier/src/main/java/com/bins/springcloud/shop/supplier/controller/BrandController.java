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
import com.bins.springcloud.shop.supplier.dto.BrandDto;
import com.bins.springcloud.shop.supplier.dto.BrandPageDto;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;
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
	
	@GetMapping("/detail")
	public ResultVo<BrandVo> detail(BrandDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<BrandVo>(1, "参数错误!", null);
		}
		ResultVo<BrandVo> result = brandService.getDetail(dto);
		return result;
	}
	
	@RequestMapping("/edit")
	public ResultVo<Boolean> edit(@RequestBody BrandDto dto) {
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		ResultVo<Boolean> result = brandService.updateBrand(dto);
		return result;
	}
	
	@RequestMapping("/add")
	public ResultVo<BrandVo> add(@RequestBody BrandDto dto) {
		if (StringUtils.isBlank(dto.getBrandName())) {
			return new ResultVo<BrandVo>(ResultCodeEnum.FAILURE.getCode(), "品牌名称不能为空!", null);
		}
		if (StringUtils.isBlank(dto.getBrandDesc())) {
			return new ResultVo<BrandVo>(ResultCodeEnum.FAILURE.getCode(), "品牌描述不能为空!", null);
		}
		return brandService.addBrand(dto);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ResultVo<Boolean> delete(BrandDto dto, HttpServletRequest req){
		if (dto.getId() == null || dto.getId() == 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "参数错误!", false);
		}
		return brandService.delBrand(dto);
	}
}
