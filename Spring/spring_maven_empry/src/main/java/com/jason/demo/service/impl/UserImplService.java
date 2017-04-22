package com.jason.demo.service.impl;

import com.jason.demo.dao.UserDao;
import com.jason.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by cjs on 2016/11/8.
 */
@Service
public class UserImplService implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void addUser(String userName, String password) {
        userDao.addUser();
    }

    @Override
    public void delUser(int userId) {
        System.out.println("----UserImplService.delUser()----");
    }

    @Override
    public String findUserById(int userId) {

        System.out.println("----UserImplService.findUserById()----");

        if(userId <= 0){
            throw new IllegalArgumentException("该用户不存在");
        }
        return "jiuqiyuliang";
    }

    @Override
    public void modifyUser(int userId, String userName, String password) {
        System.out.println("----UserImplService.modifyUser()----");
    }
}
