package com.jason.demo.service.Impl;

import com.jason.demo.dao.UserMapper;
import com.jason.demo.domain.User;
import com.jason.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cjs on 2017/4/15.
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getList() {
        return userMapper.getList();
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
