package com.bins.springcloud.shop.supplier.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bins.springcloud.shop.supplier.vo.BrandVo;

/**
 * Created by songrongbin on 2017/6/11.
 */

@FeignClient("shop-supplier")
@RequestMapping("/api/brand")
public interface BrandApi {

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public BrandVo getById(Long brandId);
    
    @RequestMapping(value = "/getByIds", method = RequestMethod.GET)
    public List<BrandVo> getByIds(List<Long> brandIds);

}
