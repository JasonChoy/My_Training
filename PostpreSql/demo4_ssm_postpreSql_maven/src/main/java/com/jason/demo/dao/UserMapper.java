package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by cjs on 2017/4/15.
 */
public interface UserMapper {
    List<User> getList();

    void addUser(User user);
}
