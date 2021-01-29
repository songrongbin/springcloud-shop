package com.bins.trade;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TradeApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(TradeApplication.class).run(args);
    }
}
