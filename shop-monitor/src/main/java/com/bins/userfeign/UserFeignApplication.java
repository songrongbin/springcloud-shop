package com.bins.userfeign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UserFeignApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(UserFeignApplication.class).run(args);
    }
}
