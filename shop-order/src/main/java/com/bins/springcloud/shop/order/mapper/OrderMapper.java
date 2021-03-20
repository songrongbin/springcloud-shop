package com.bins.springcloud.shop.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.order.entity.OrderEntity;

@Mapper
public interface OrderMapper {

	List<OrderEntity> findList();
}
