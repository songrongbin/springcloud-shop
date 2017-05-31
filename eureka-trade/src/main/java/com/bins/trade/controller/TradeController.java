package com.bins.trade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2017/5/26.
 */
@RestController
public class TradeController {
    @RequestMapping("/trade")
    public String home() {
        return "Hello trade";
    }

}
