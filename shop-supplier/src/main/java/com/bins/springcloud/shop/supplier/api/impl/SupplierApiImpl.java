package com.bins.springcloud.shop.supplier.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springcloud.shop.supplier.api.SupplierApi;
import com.bins.springcloud.shop.supplier.service.SupplierService;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;

@RestController
public class SupplierApiImpl implements SupplierApi {
	
	@Autowired
	private SupplierService supplierService;

	@Override
	public SupplierVo getSupplierById(Long id) {
		return supplierService.getById(id);
	}

	@Override
	public List<SupplierVo> getSupplierByIds(List<Long> ids) {
		return supplierService.getByIds(ids);
	}

}
