package com.mgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableTurbine  //开启turbine
@EnableHystrixDashboard  //启用监控
@EnableCircuitBreaker  //启用熔断
@EnableEurekaClient
public class TurbineApplication {

    /**
     * http://localhost:7002/hystrix
     * http://localhost:7002/turbine.stream
     * https://blog.csdn.net/z8414/article/details/78598872
     */
    public static void main(String[] args) {
        SpringApplication.run(TurbineApplication.class, args);
    }
}
