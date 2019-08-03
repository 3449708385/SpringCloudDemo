package com.mgp.customer.controller;

import com.mgp.commons.bean.User;
import com.mgp.customer.service.DemoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    public DemoFeignService demoFeignService;

    @RequestMapping("/test")
    public String test() {
        return demoFeignService.test("test");
    }

    @RequestMapping("/user")
    public User user() {
        User user = new User();
        user.setId(10L);
        user.setUsername("Joab-Y");
        return demoFeignService.user(user);
    }
}
