package com.bins.userfeign;

import com.bins.user.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user")
public interface UserFeignClient {

    @RequestMapping(value ="/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
