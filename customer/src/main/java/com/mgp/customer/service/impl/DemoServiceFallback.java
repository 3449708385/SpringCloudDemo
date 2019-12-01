package com.mgp.customer.service.impl;

import com.mgp.commons.bean.User;
import com.mgp.customer.service.DemoFeignService;
import org.springframework.stereotype.Service;

@Service("demoFeignService")
public class DemoServiceFallback implements DemoFeignService {
    @Override
    public String test(String test) {
        return "error";
    }

    @Override
    public User user(User user) {
        return null;
    }
}
