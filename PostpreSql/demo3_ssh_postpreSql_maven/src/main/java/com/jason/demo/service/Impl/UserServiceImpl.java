package com.jason.demo.service.Impl;

import com.jason.demo.dao.IUserDao;
import com.jason.demo.dao.Impl.UserDao;
import com.jason.demo.domain.User;
import com.jason.demo.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by cjs on 2017/4/14.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{
    @Resource
    private IUserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    public ArrayList<User> getList() {
        return userDao.getList();
    }
}
/*
不加@Transactional会出现异常
org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread
*/
