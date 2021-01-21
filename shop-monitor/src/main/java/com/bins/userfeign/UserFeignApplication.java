package com.bins.userfeign;

import com.bins.user.UserApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UserFeignApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(UserFeignApplication.class).web(true).run(args);
    }
}
