package com.mgp.customer.dao;


import com.mgp.commons.bean.User;
import com.mgp.commons.bean.UserExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {

    // @Select("select * from sys_user"),舍弃这种方式
    public List<User> queryByAll();

    // @Select("select * from sys_user where username=#{username}"),舍弃这种方式
    public List<User> queryByUserName(String username);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}