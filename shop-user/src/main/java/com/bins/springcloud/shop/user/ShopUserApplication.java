package com.bins.springcloud.shop.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//////@EnableFeignClients(basePackages = "com.bins.springcloud.shop.user.api")
public class ShopUserApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ShopUserApplication.class).run(args);
    }
}
