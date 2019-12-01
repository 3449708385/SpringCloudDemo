package com.mgp.provider.service.impl;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.mgp.commons.bean.User;
import com.mgp.provider.dao.UserMapper;
import com.mgp.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
    public void insertUser(User user) {
        userMapper.insert(user);
    }


}
