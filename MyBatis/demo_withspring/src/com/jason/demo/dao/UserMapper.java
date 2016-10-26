package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by cjs on 2016/9/23.
 * mapper接口的方法 实现dao
 */
public interface UserMapper {
    User findUserById(int id);
    List<User> findUserByUserName(User user);

//    使用注解的方式可以不用写xml映射文件
//    @Select("SELECT * FROM  user  WHERE id=#{user_id}")
//    User findUserById(@Param("user_id") int id);
}
