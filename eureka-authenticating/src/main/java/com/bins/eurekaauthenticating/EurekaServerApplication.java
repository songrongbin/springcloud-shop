package com.bins.eurekaauthenticating;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 默认端口8761
 * http://127.0.0.1:8761/
 */
@EnableEurekaServer //启动一个服务注册中心提供给其他应用进行对话
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        //下面两行代码都可以用来启动
        new SpringApplicationBuilder(EurekaServerApplication.class).web(true).run(args);
    }
}
