package com.bins.springcloud.shop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.bins.springcloud.shop.product.entity.GoodsEntity;

@Mapper
public interface GoodsMapper {

	List<GoodsEntity> findList();
}
