package com.bins.springcloud.shop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.order.dto.OrderPageDto;
import com.bins.springcloud.shop.order.service.OrderService;
import com.bins.springcloud.shop.order.vo.OrderVo;
import com.github.pagehelper.PageInfo;

/**
 * Created by songrongbin on 2017/5/25.
 */

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/pagination")
	public ResultVo<PageInfo<OrderVo>> pageList(OrderPageDto pageDto) {
		PageInfo<OrderVo> pageInfo = orderService.getPagination(pageDto);
		ResultVo<PageInfo<OrderVo>> result = new ResultVo<PageInfo<OrderVo>>();
		result.setData(pageInfo);
		return result;
	}

}
