package com.bins.springcloud.shop.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.product.dto.GoodsPageDto;
import com.bins.springcloud.shop.product.service.GoodsService;
import com.bins.springcloud.shop.product.vo.GoodsVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@GetMapping("/pagination")
	public ResultVo<PageInfo<GoodsVo>> pageList(GoodsPageDto pageDto) {
		PageInfo<GoodsVo> pageInfo = goodsService.pagination(pageDto);
		ResultVo<PageInfo<GoodsVo>> result = new ResultVo<PageInfo<GoodsVo>>();
		result.setData(pageInfo);
		return result;
	}

}