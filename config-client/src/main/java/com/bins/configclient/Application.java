package com.bins.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2016/9/28.
 */
@SpringBootApplication
@RestController
public class Application {
    // @Value("${foo:World222!}")

    @Value("${foo}")
    private String foo;

    @RequestMapping("/client")
    String hello() {
        return "Hello " + foo + "!";
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("foo from env: "
                + env.getProperty("foo"));
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
