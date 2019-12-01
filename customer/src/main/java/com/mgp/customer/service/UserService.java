package com.mgp.customer.service;

import com.mgp.commons.bean.User;

import java.util.List;

public interface UserService {

    public List<User> queryUserList();
    public List<User> queryUserListByUserName(String username);
    public void insertUser(User user);

    //Lcn分布式事物测试
    public int lcnTest();
}
