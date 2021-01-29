package com.bins.springcloud.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bins.springcloud.user.api")
public class ShopUserApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ShopUserApiApplication.class).run(args);
    }
}
