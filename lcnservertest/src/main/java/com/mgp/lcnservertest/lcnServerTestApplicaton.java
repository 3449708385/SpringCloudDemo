package com.mgp.lcnservertest;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mgp.lcnservertest.service")
@EnableHystrix   //容错保护
//LCN zhu
@EnableDistributedTransaction
@MapperScan(basePackages = "com.mgp.*.dao")
public class lcnServerTestApplicaton {

    /*@Bean
    public HttpHeaders myHeaders() { // 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders(); // 定义一个HTTP的头信息
        String auth = "admin:admin"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        // 在进行授权的头信息内容配置的时候加密的信息一定要与“Basic”之间有一个空格
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
*/

    /**
     * @LoadBalanced 取出服务list，根据负载均衡算法分配服务
     * @return
     */
    @Bean //对象注入容器
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    /**
     * http://localhost:8081/test
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(lcnServerTestApplicaton.class, args);
    }
}
