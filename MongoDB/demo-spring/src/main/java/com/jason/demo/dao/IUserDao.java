package com.jason.demo.dao;

import com.jason.demo.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface IUserDao {
    public void add(User user);
    public void del(Integer id);
    public void update(User user);
    public List<User> findAll();
    public User findById(Integer id);
    public List<User> findByPage(int pageSize, int pageNum);
}
