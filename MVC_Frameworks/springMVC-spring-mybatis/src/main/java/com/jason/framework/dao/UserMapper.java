package com.jason.framework.dao;

import com.jason.framework.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface UserMapper {
    List<User> getAll();
    User getByid(Integer id);
    void insert(User user);
    void update(User user);
    User delete(Integer id);
}
