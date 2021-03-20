package com.bins.springcloud.shop.order.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.bins.springcloud.shop.user.api.UserApi;

// @FeignClient("shop-user")
public interface UserFeignService extends UserApi {

}
