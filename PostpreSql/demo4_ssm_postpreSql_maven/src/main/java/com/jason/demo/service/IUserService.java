package com.jason.demo.service;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by cjs on 2017/4/15.
 */
public interface IUserService {
    List<User> getList();

    void addUser(User user);
}
