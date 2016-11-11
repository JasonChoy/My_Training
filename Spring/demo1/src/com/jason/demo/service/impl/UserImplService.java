package com.jason.demo.service.impl;

import com.jason.demo.dao.UserDao;
import com.jason.demo.service.UserService;

/**
 * Created by cjs on 2016/11/8.
 */
public class UserImplService implements UserService{
    private UserDao userDao;

/*    setter注入*/
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
/*     构造器注入
    public UserImplService(UserDao userDao) {
        this.userDao = userDao;
    }
*/

    @Override
    public void addUser(String userName, String password) {
        userDao.addUser(userName,password);
    }
}
