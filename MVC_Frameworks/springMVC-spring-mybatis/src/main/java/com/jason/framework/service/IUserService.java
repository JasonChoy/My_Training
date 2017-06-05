package com.jason.framework.service;

import com.jason.framework.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface IUserService {
    List<User> getAll();
    User getByid(Integer id);
    void insert(User user);
    void update(User user);
    User delete(Integer id);
}
