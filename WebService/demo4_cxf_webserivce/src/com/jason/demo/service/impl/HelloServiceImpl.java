package com.jason.demo.service.impl;

import com.jason.demo.pojo.User;
import com.jason.demo.service.IHelloService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by cjs on 2017/4/12.
 */
@WebService(endpointInterface = "com.jason.demo.service.IHelloService",serviceName="JasonCaiHello")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello() {
        String massage = "jasoncai say lao tie 666!";
        System.out.println("服务端hello被调用...");
        return massage;
    }

    @Override
    public String bye() {
        String massage = "jasoncai say blue who say and whose!";
        System.out.println("服务端bye被调用...");
        return massage;
    }

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
