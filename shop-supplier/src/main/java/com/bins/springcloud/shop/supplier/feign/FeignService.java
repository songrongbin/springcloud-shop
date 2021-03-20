package com.bins.springcloud.shop.supplier.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "shop-user") 
public interface FeignService {

	@RequestMapping("/get")
	public String get();
}