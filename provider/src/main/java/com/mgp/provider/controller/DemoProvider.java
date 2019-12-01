package com.mgp.provider.controller;

import com.mgp.api.service.IDemoApi;
import com.mgp.api.service.LcnApi;
import com.mgp.commons.bean.User;
import com.mgp.provider.service.LcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoProvider implements IDemoApi {

    @Override
    public String test(String test) {
        return "test: " + test;
    }

    @Override
    public User user(@RequestBody User user) {
        if (user.getId() == null) {
            user = new User();
            user.setNickname("mm");
            user.setUsername("mgp");
        }
        return user;
    }

}
