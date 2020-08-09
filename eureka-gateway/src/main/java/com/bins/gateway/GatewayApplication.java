package com.bins.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by songrongbin on 2017/7/27.
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {
//    public static void main(String[] args) {
//        // new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
//    }

}
