package com.bins.eurekaclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2016/9/28.
 */
@SpringBootApplication
@RestController
//@EnableAutoConfiguration
@EnableEurekaClient
public class Application {
    @RequestMapping("/home")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
