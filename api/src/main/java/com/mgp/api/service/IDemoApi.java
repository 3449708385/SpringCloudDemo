package com.mgp.api.service;

import com.mgp.commons.bean.User;
import org.springframework.web.bind.annotation.*;

public interface IDemoApi {
    /**
     * 入参为字段，返回值为字段
     * 注意：

     1）使用security 后就不支持@GetMapping @PostMapping，只能用@RequestMapping，
        通过method指定请求方式（报错是IDemoApi bean无法注入，未指定请求类型post或get)；

     2）参数传递必须用@RequestParam(value = "test") 注解修饰；

     3）传递的参数为对象，必须用@RequestBody修饰；

     4）返回值若为对象，对象必须序列化，且必须提供public修饰的无参构造方法（默认是提供的），会报错com.fasterxml.jackson.databind.exc.InvalidDefinitionException，原因是jackson的反序列化需要无参构造函数；
     */
    @RequestMapping(value="/demo-api/test", method= RequestMethod.GET)
    String test(@RequestParam(value = "test") String test);
    /**
     * 入参为对象，返回值为对象
     */
    //@RequestMapping(value="/demo-api/user", method=RequestMethod.POST)
    @RequestMapping(value="/demo-api/user", method = RequestMethod.POST)
    User user(@RequestBody User user);
}
