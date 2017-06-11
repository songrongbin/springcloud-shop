package com.bins.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2017/6/12.
 */
@RestController
@RefreshScope
public class LuckyWordRefreshController {
    @Value("${wordConfig.luckyWord}")
    String luckyWord;

    @RequestMapping("/lucky-word")
    public String showLuckyWord() {
        return "The lucky word is: " + luckyWord;
    }
}
