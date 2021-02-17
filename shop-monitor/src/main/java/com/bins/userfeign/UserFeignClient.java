package com.bins.userfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bins.springcloud.shop.user.dto.UserDto;

@FeignClient(name = "user")
public interface UserFeignClient {

    @RequestMapping(value ="/user/{id}", method = RequestMethod.GET)
    public UserDto findById(@PathVariable("id") Long id);
}
