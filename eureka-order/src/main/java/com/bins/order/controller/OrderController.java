package com.bins.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2017/5/25.
 */
@RestController
public class OrderController {
    @RequestMapping("/order")
    public String home() {
        return "Hello order";
    }

}
