package com.bins.sentence.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by songrongbin on 2017/5/25.
 */
@RestController
public class SentenceController {

    private Logger logger = LoggerFactory.getLogger(SentenceController.class);

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/sentence")
    public String getSentence() {
        String order = getWord("order") + " ";
        String trade = getWord("trade") + " ";
        String user = getWord("user") + ".";
        logger.info("order:" + order + ",trade:" + trade + ",user:" + user);
        return order + trade + user;
    }

    public String getWord(String service) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri !=null ) {
                String template = (new RestTemplate()).getForObject(uri + "/" + service, String.class);
                return template;
            }
        }
        return null;
    }
}
