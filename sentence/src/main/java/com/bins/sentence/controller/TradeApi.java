package com.bins.sentence.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by songrongbin on 2017/6/11.
 */
@RestController
public class TradeApi {
    private Logger logger = LoggerFactory.getLogger(SentenceController.class);

    @Autowired
    LoadBalancerClient loadBalancer;

    @RequestMapping("/sentence")
    public String getSentence() {
        String trade = getWord("trade") + " ";
        return trade;
    }

    public String getWord(String service) {
        ServiceInstance instance = loadBalancer.choose(service);
        return (new RestTemplate()).getForObject(instance.getUri(), String.class);
    }

}
