package com.mgp.txlcn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class LCNApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(LCNApplication.class, args);
	}

}
