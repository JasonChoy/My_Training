package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface IUserDao {
    void add(User user);
    void del(Integer id);
    void update(User user);
    List<User> findAll();
    User findById(Integer id);
    List<User> findByPage(int pageSize, int pageNum);
}
