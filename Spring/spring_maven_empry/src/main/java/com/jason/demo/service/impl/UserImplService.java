package com.jason.demo.service.impl;

import com.jason.demo.service.UserService;

/**
 * Created by cjs on 2016/11/8.
 */
public class UserImplService implements UserService {
//    private UserDao userDao;
//
///*    setter注入*/
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public void addUser(String userName, String password) {
        System.out.println("----UserImplService.add()----");
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
