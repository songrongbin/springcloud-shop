package com.bins.sentence.api;

import com.bins.springcloud.user.dto.UserDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by songrongbin on 2017/6/11.
 */
@FeignClient("shop-user")
public interface UserApi {

    @RequestMapping(value = "/getPersonInfo", method = RequestMethod.GET)
    public UserDto getPersonInfo(Long userId);

}
