package com.mgp.provider.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.mgp.commons.bean.User;
import com.mgp.provider.service.LcnService;
import com.mgp.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LcnServiceImpl implements LcnService {

    @Autowired
    public UserService userService;

    @Transactional
    @LcnTransaction(propagation = DTXPropagation.SUPPORTS) //分布式事务注解
    @Override
    public int insertSigleUser(User user) {
        user.setNickname("lcn2");
        userService.insertUser(user);
        return 0;
    }
}
