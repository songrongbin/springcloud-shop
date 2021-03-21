package com.bins.springcloud.shop.supplier.service;

import java.util.List;

import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.github.pagehelper.PageInfo;

public interface SupplierService {


	public PageInfo<SupplierVo> getSupplierPagination(SupplierPageDto pageDto);

	public SupplierVo getById(Long id);

	public List<SupplierVo> getByIds(List<Long> ids);

}
