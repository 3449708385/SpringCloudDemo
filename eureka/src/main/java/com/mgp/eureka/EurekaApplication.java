package com.mgp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

	/**
	 * http://localhost:8761/
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class);
	}
}
