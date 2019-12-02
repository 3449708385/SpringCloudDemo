package com.mgp.provider.controller;

import com.mgp.commons.bean.User;
import com.mgp.provider.service.LcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private LcnService lcnService;

    @RequestMapping("/insertUserData")
    public void insertUserData(){
        User user = new User();
        user.setNickname("test");
        user.setUsername("test");
        lcnService.insertSigleUser(user);
    }
}
