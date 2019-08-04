package com.mgp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {

    /**
     * http://localhost:8091/demo-api/test?test=123
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }
}
