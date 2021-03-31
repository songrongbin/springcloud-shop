package com.bins.springcloud.shop.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.supplier.dto.SupplierDto;
import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.entity.SupplierEntity;

@Mapper
public interface SupplierMapper {

	public SupplierEntity findById(Long id);

	public List<SupplierEntity> findSupplierList(SupplierPageDto pageDto);

	public List<SupplierEntity> findByIds(List<Long> ids);

	public int updateById(SupplierDto dto);

	public int insert(SupplierDto dto);

	public int deleteById(SupplierDto dto);

}
