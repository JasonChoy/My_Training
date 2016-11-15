package com.jason.demo.controller;

import com.jason.demo.handler.LogHandler;
import com.jason.demo.service.UserManager;
import com.jason.demo.service.impl.UserManagerImpl;
import org.junit.Test;

/**
 * Created by cjs on 2016/11/15.
 */
public class Client {
    @Test
    public void test(){
        LogHandler logHandler = new LogHandler();
        UserManager userManager = (UserManager)logHandler.newProxyInstance(new UserManagerImpl());
        userManager.addUser("1","jasonchoy");
    }
}
