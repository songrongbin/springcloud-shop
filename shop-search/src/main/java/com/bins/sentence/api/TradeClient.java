package com.bins.sentence.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by songrongbin on 2017/6/11.
 */
@FeignClient("shop-trade")
public interface TradeClient {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWord();

}
