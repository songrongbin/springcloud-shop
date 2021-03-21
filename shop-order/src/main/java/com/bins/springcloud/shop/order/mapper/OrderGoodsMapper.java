package com.bins.springcloud.shop.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.order.entity.OrderEntity;
import com.bins.springcloud.shop.order.entity.OrderGoodsEntity;

@Mapper
public interface OrderGoodsMapper {

	OrderEntity findById(Long id);

	List<OrderGoodsEntity> findListByOrderId(Long orderId);
}
