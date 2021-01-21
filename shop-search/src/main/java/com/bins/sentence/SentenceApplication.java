package com.bins.sentence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

/**
 * Created by songrongbin on 2017/5/25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@EnableHystrix
@EnableHystrixDashboard
public class SentenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentenceApplication.class, args);
    }

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }

}
