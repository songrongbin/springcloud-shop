package com.bins.movie.controller;

import com.bins.movie.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public UserModel findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://userprovider/" + id, UserModel.class);
    }

    @GetMapping("/log-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("userprovider");
        System.out.println(serviceInstance.getServiceId());
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getPort());
    }

    @GetMapping("/movie/user/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        System.out.println("url:" + this.userServiceUrl);
        return this.restTemplate.getForObject(this.userServiceUrl + id, UserModel.class);
    }
}
