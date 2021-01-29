package com.bins.sentence.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by songrongbin on 2017/6/11.
 */
@FeignClient("order")
public interface OrderClient {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWord();

}
