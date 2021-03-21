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
public interface BrandApi {

    @RequestMapping(value = "/getBrandById", method = RequestMethod.GET)
    public BrandVo getBrandById(Long brandId);
    
    @RequestMapping(value = "/getBrandByIds", method = RequestMethod.GET)
    public List<BrandVo> getBrandByIds(List<Long> brandIds);

}
