package com.bins.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songrongbin on 2016/9/28.
 * http://localhost:8888/spingCloudConfServer/config-server
 * 其实应该是文件的名称，这个又可以与项目名称对应
 */
@Configuration
@EnableAutoConfiguration
@RestController
@EnableConfigServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
