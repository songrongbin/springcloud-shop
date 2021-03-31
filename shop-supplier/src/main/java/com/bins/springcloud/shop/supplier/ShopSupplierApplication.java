package com.bins.springcloud.shop.supplier;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.bins.springcloud.shop.user.api"})
public class ShopSupplierApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ShopSupplierApplication.class).run(args);
    }
}
