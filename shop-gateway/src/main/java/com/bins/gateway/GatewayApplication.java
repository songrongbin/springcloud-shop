package com.bins.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Created by songrongbin on 2017/7/27.
 */
// @EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GatewayApplication.class).run(args);
    }

}
