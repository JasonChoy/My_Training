package com.jason.demo.controller;


import com.jason.demo.factory.ClassPathXmlApplicationContext3;
import com.jason.demo.service.TestService;

/**
 * Created by cjs on 2016/11/8.
 */
public class TestContoller {
    public static void main(String[] args) {
        //配置文件+反射实现IoC容器
        ClassPathXmlApplicationContext3 classPathXmlApplicationContext = new ClassPathXmlApplicationContext3("test.xml");
        TestService testService = (TestService)classPathXmlApplicationContext.getBean("service");
        //调用Service实现的方法
        testService.serviceMethod();
    }

}
