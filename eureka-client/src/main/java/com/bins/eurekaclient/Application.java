package com.bins.eurekaclient;



/**
 * Created by songrongbin on 2016/9/28.
 */
//@SpringBootApplication
//@RestController
//@EnableAutoConfiguration
public class Application {
    //@RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {

        //new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}
