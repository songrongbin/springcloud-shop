package com.bins.user.controller;

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

}
