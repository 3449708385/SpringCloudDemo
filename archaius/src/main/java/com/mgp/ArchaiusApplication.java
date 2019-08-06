package com.mgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@SpringBootApplication
public class ArchaiusApplication {
    /**
     * 可以在多个文件里面取值，不用切换文件，相同属性以最后读取的为准
     * http://127.0.0.1:7003/properties-from-dynamic
     * 启动项指定或static代码指定：yml不用配置
     * System.setProperty("archaius.configurationSource.defaultFileName", "customConfig.properties");
     * archaius.configurationSource.defaultFileName：类路径中的默认配置文件名
     archaius.fixedDelayPollingScheduler.initialDelayMills：读取配置源之前的初始延迟
     archaius.fixedDelayPollingScheduler.delayMills：两次读取源之间的延迟; 默认值为1分钟
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ArchaiusApplication.class,args);
    }
}
