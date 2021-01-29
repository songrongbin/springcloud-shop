package com.bins.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by songrongbin on 2016/9/28.
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(OrderApplication.class).run(args);
    }
}
