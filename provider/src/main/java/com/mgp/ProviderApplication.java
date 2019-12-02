package com.mgp;


import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.mgp.*.dao")
//LCN 客户端注解
@EnableDistributedTransaction
public class ProviderApplication {

    /**
     * http://localhost:8091/demo-api/test?test=123
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class);
    }
}
