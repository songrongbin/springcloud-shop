package com.bins.springcloud.shop.user.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bins.springcloud.shop.user.vo.UserVo;

@FeignClient("shop-user")
public interface UserApi {

	@RequestMapping(value = "/getById")
    public UserVo getById(@RequestParam(value = "id") Long id);
    
    @RequestMapping(value = "/getByIds")
    public List<UserVo> getByIds(@RequestParam(value = "ids") List<Long> ids);
    
    @GetMapping("/getUserName")
    String getUserName(@RequestParam(value = "id") Long id);
}
