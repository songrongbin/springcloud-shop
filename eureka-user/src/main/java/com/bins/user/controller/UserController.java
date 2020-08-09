package com.bins.user.controller;

import com.bins.user.model.User;
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
    public User findByUserId(@PathVariable Long id) {
        User user = new User();
        user.setId(11l);
        user.setName("andy");
        return user;
    }

    @RequestMapping("{id}")
    public User findById(@PathVariable Long id) {
        User user = new User();
        user.setId(11l);
        user.setName("andy");
        return user;
    }

}
