package com.jason.demo.service.impl;

import com.jason.demo.pojo.User;
import com.jason.demo.service.IMyService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by cjs on 2016/11/16.
 * 此类称为服务实现Bean，SIB（Serviceimplemention Bean）   endpointInterface属性指向接口。
 */
@WebService(endpointInterface="com.jason.demo.service.IMyService") //endpointInterface：接入点接口
public class MyServiceImpl implements IMyService {
    @WebMethod
    @Override
    public User login(String username, String password) {

        System.out.println(username + "is logining");

        User user = new User();

        user.setId(1);
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }
}
