package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by cjs on 2017/5/2.
 */
public interface IUserDao {
    void saveOrUpdateUser(User user);
    List<User> getAll();
    User getById(Integer id);
    void delById(Integer id);

}
