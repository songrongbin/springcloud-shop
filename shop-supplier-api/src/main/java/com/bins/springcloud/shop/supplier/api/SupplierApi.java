package com.bins.springcloud.shop.supplier.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bins.springcloud.shop.supplier.vo.SupplierVo;

/**
 * Created by songrongbin on 2017/6/11.
 */

@FeignClient("shop-supplier")
public interface SupplierApi {

    @RequestMapping(value = "/getSupplierById")
    public SupplierVo getSupplierById(@RequestParam(value = "id") Long id);
    
    @RequestMapping(value = "/getSupplierByIds")
    public List<SupplierVo> getSupplierByIds(@RequestParam(value = "ids") List<Long> ids);

}
