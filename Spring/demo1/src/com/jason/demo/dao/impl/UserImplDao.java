package com.jason.demo.dao.impl;

import com.jason.demo.dao.UserDao;

/**
 * Created by cjs on 2016/11/8.
 */
public class UserImplDao implements UserDao{
    @Override
    public void addUser(String userName, String password) {
        System.out.println("UserDao4MySqlImpl.addUser()");
        System.out.println("userName="+userName);
        System.out.println("password="+password);
    }
}
