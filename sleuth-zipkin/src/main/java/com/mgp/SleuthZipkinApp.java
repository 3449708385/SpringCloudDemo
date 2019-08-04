package com.mgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @EnableZipkinServer
 *
 * 用于开启Zipkin Server功能
 *
 */
@EnableZipkinServer
@SpringBootApplication
@EnableEurekaClient
public class SleuthZipkinApp {

    /**
     * http://localhost:7001/zipkin/
     */
    public static void main(String[] args) {
        SpringApplication.run(SleuthZipkinApp.class, args);
    }

}
