package com.bins.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2016/9/28.
 */
@SpringBootApplication
@RestController
public class Application {
    @Value("${name:World222!}")
    String bar;

    @RequestMapping("/client")
    String hello() {
        return "Hello " + bar + "!";
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
