package com.bins.sentence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by songrongbin on 2017/5/25.
 */
@SpringBootApplication
//@EnableEurekaServer
@EnableEurekaClient
@EnableFeignClients
public class SentenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentenceApplication.class, args);
    }

}
