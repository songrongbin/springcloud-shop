package com.bins.user.controller;

import com.bins.user.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2017/5/26.
 */
@RestController
public class UserController {
    @RequestMapping("/user")
    public String home() {
        return "Hello user";
    }

    @RequestMapping("/user/{id}")
    public UserDto findByUserId(@PathVariable Long id) {
        UserDto user = new UserDto();
        user.setId(11l);
        user.setName("andy");
        return user;
    }

    @RequestMapping("{id}")
    public UserDto findById(@PathVariable Long id) {
        UserDto user = new UserDto();
        user.setId(11l);
        user.setName("andy");
        return user;
    }

}
