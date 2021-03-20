package com.bins.springcloud.shop.supplier.service;

import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.github.pagehelper.PageInfo;

public interface SupplierService {


	public PageInfo<SupplierVo> getSupplierPagination(SupplierPageDto pageDto);

}
