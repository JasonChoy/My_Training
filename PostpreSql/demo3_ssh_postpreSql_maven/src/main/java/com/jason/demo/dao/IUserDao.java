package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.ArrayList;

/**
 * Created by cjs on 2017/4/14.
 */
public interface IUserDao {
    public Integer addUser(User user);

    ArrayList<User> getList();
}
