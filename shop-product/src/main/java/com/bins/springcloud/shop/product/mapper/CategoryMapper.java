package com.bins.springcloud.shop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.product.entity.CategoryEntity;

@Mapper
public interface CategoryMapper {

	List<CategoryEntity> findList();

	CategoryEntity findById(Long brandId);

	List<CategoryEntity> getByIds(List<Long> ids);
}
