package com.mgp.provider.controller;

import com.mgp.api.service.LcnApi;
import com.mgp.commons.bean.User;
import com.mgp.provider.service.LcnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LcnApiService implements LcnApi {

    @Autowired
    private LcnService lcnService;

    @Override
    public int insertData(@RequestBody User user) {
        if(user!=null){
            return lcnService.insertSigleUser(user);
        }else{
            throw new RuntimeException();
        }
    }
}
