package com.bins.springcloud.shop.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by songrongbin on 2016/9/28.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.bins.springcloud.shop.user.api", "com.bins.springcloud.shop.supplier.api"})
public class ShopOrderApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ShopOrderApplication.class).run(args);
    }
}
