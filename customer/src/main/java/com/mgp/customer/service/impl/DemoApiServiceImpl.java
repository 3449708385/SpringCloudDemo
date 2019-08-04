package com.mgp.customer.service.impl;

import com.mgp.commons.bean.User;
import com.mgp.customer.service.DemoApiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service("demoApiService")
public class DemoApiServiceImpl implements DemoApiService {

    @Autowired(required = false)@Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * 入参为字段，返回值为字段
     * 注意：
     * <p>
     * 1）不支持@GetMapping @PostMapping，只能用@RequestMapping，通过method指定请求方式；
     * <p>
     * 2）参数传递必须用@RequestParam(value = "test") 注解修饰；
     * <p>
     * 3）传递的参数为对象，必须用@RequestBody修饰；
     * <p>
     * 4）返回值若为对象，对象必须序列化，且必须提供public修饰的无参构造方法（默认是提供的），会报错com.fasterxml.jackson.databind.exc.InvalidDefinitionException，原因是jackson的反序列化需要无参构造函数；
     *
     * @param test
     */
    @Override
    @HystrixCommand(fallbackMethod = "getTest") //进行容错处理, 出现异常，容错保护就会调用
    public String test(String test) {
        String serverName = "demo-provider";  //再框架里面可以访问，外面不行
        String url = "http://"+serverName+"/demo-api/test?test="+test;
        return restTemplate.getForObject(url, String.class);//这是服务间相互调用
    }

    /**
     * 入参为对象，返回值为对象
     *
     * @param user
     */
    @Override
    @HystrixCommand(fallbackMethod = "getUserInfoError") //进行容错处理, 出现异常，容错保护就会调用
    public User user(User user) {
        MultiValueMap<String, User> paramMap = new LinkedMultiValueMap<>();//post传参
        paramMap.add("user", user);
        String serverName = "demo-provider";  //再框架里面可以访问，外面不行
        String url = "http://"+serverName+"/demo-api/user";
        return restTemplate.getForObject(url, User.class, paramMap);//这是服务间相互调用
    }

    //getUserInfo 失败执行的方法
    private User getUserInfoError(User user){
        User userTemp = new User();
        userTemp.setId(0L);
        userTemp.setNickname("nickname");
        userTemp.setUsername("username");
        return userTemp;
    }

    private String getTest(String test){
          return "error";
    }
}
