package com.mgp.provider.service;

import com.mgp.commons.bean.User;

import java.util.List;

public interface UserService {

    public List<User> queryUserList();
    public List<User> queryUserListByUserName(String username);
    public void insertUser(User user);
}
