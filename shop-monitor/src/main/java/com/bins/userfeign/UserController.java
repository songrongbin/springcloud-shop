package com.bins.userfeign;

import com.bins.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping("/user/{id}")
    public UserDto findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }
}
