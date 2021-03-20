package com.bins.springcloud.shop.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.supplier.dto.BrandDto;
import com.bins.springcloud.shop.supplier.entity.BrandEntity;

@Mapper
public interface BrandMapper {

	public int insert(BrandDto dto);

	public int updateById(BrandDto dto);

	public BrandEntity findById(Long id);

	public List<BrandEntity> findList();

	public List<BrandEntity> getByIds(List<Long> ids);

	public int delete(BrandDto dto);

}
