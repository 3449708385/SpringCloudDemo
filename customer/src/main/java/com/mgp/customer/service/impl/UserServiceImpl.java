package com.mgp.customer.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.mgp.commons.bean.User;
import com.mgp.customer.dao.UserMapper;
import com.mgp.customer.service.LcnService;
import com.mgp.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public LcnService lcnService;

    @Override
    public List<User> queryUserList() {
       // int i = 10/0;
        return userMapper.queryByAll();
    }

    @Override
    public List<User> queryUserListByUserName(String username) {
        return userMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insert(user);
    }


    @Override
    @LcnTransaction(propagation= DTXPropagation.REQUIRED)
    @Transactional(rollbackFor = Exception.class)
    public int lcnTest() {
        User user = new User();
        user.setUsername("mm");
        user.setNickname("lcn");
        //该项目插入
        userMapper.insert(user);
        //其他项目远程插入
        lcnService.insertData(user);
        return 0;
    }
}
