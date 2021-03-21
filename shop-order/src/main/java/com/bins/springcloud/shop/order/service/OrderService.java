package com.bins.springcloud.shop.order.service;

import com.bins.springcloud.shop.order.dto.OrderDto;
import com.bins.springcloud.shop.order.dto.OrderPageDto;
import com.bins.springcloud.shop.order.vo.OrderDetailVo;
import com.bins.springcloud.shop.order.vo.OrderVo;
import com.github.pagehelper.PageInfo;

public interface OrderService {

	PageInfo<OrderVo> getPagination(OrderPageDto pageDto);

	OrderVo getById(OrderDto dto);

	OrderDetailVo getOrderDetailById(OrderDto dto);

}
