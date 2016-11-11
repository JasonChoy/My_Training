package com.jason.demo.controller;

import com.jason.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cjs on 2016/11/8.
 */
public class UserContoller {
    UserService userService = null;
    @Before
    public void init(){
        //由我们的应用程序负责服务（对象）定位
        //这是一个组装过程，userManager用userDao，这种关系是用程序描述的，现在用配置文件描述
        //UserManager userManager = new UserManagerImpl(new UserDao4MySqlImpl());
        //userManager.addUser("张三", "123");

        //BeanFactory是一个接口，有不同的实现，ClassPathXmlApplicationContext是对beanFactory的实现
        //把applicationContext.xml读进来
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");

        //ApplicationContext都实现了beanFactory的接口
        //ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");

        //getBean加产品标识，相当与userManager的实现，再转化为userManager的接口
        userService =(UserService)factory.getBean("userManager");
    }
    @Test
    public void addUser(){
        userService.addUser("jason","123");
    }

}
