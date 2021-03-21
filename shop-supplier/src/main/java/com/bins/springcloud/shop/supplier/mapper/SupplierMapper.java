package com.bins.springcloud.shop.supplier.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bins.springcloud.shop.supplier.dto.SupplierDto;
import com.bins.springcloud.shop.supplier.entity.SupplierEntity;

@Mapper
public interface SupplierMapper {

	public int insertSupplier(SupplierDto dto);

	public int updateSupplier(SupplierDto dto);

	public SupplierEntity findById(Long id);

	public List<SupplierEntity> findSupplierList();

	public List<SupplierEntity> findByIds(List<Long> ids);

	public int deleteSupplier(SupplierDto dto);

}
