package com.bins.springcloud.shop.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShopProductApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ShopProductApplication.class).run(args);
    }
}
