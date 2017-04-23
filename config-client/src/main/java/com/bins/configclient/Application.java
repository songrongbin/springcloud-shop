package com.bins.configclient;



/**
 * Created by songrongbin on 2016/9/28.
 */
//@SpringBootApplication
//@RestController
public class Application {
    // @Value("${name:World!}")
    String bar;

    // @RequestMapping("/")
    String hello() {
        return "Hello " + bar + "!";
    }

    public static void main(String[] args) {

        // SpringApplication.run(Application.class, args);
    }
}
