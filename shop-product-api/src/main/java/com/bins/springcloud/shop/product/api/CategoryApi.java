package com.bins.springcloud.shop.product.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bins.springcloud.shop.product.vo.CategoryVo;

@FeignClient("shop-product")
@RequestMapping("/api/category")
public interface CategoryApi {
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
    public CategoryVo getById(Long brandId);
    
    @RequestMapping(value = "/getByIds", method = RequestMethod.GET)
    public List<CategoryVo> getByIds(List<Long> ids);

}
