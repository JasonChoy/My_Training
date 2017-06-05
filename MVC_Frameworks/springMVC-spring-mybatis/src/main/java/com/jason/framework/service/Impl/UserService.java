package com.jason.framework.service.Impl;

import com.jason.framework.dao.UserMapper;
import com.jason.framework.domain.User;
import com.jason.framework.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
@Service
public class UserService implements IUserService{
    @Autowired
    UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public User getByid(Integer id) {
        return null;
    }

    public void insert(User user) {

    }

    public void update(User user) {

    }

    public User delete(Integer id) {
        return null;
    }
}
