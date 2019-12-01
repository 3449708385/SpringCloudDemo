package com.mgp.api.service;

import com.mgp.commons.bean.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
  用于测试lcn分布式事务

 */
public interface LcnApi {

    //插入数据,Lcn Test
    @RequestMapping(value="insertData", method= RequestMethod.POST)
    int insertData(@RequestBody User user);

}
